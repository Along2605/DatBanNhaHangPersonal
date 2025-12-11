package dao;

import entity.*;
import entity.NhatKyThaoTac.LoaiThaoTac;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import connectDB.ConnectDB;

public class ChiTietHoaDonDAO {
    private static ChiTietHoaDonDAO instance;
    private final NhatKyThaoTacDAO logDAO = NhatKyThaoTacDAO.getInstance();

    public static ChiTietHoaDonDAO getInstance() {
        if (instance == null) {
            instance = new ChiTietHoaDonDAO();
        }
        return instance;
    }

    public ChiTietHoaDonDAO() {}

    // ===================================================================
    // 1. THÊM CHI TIẾT HÓA ĐƠN (có trừ tồn kho + cập nhật tổng tiền + log)
    // ===================================================================
    public boolean themChiTietHoaDon(ChiTietHoaDon cthd, String maNVThaoTac) {
        Connection con = null;
        boolean originalAutoCommit = true;

        try {
            con = ConnectDB.getConnection();
            if (con == null || con.isClosed()) {
                ConnectDB.getInstance().connect();
                con = ConnectDB.getConnection();
            }

            originalAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);

            MonAnDAO monAnDAO = new MonAnDAO();

            // Trừ tồn kho
            if (!monAnDAO.truTonKho(con, cthd.getMonAn().getMaMon(), cthd.getSoLuong(), maNVThaoTac)) {
                MonAn mon = monAnDAO.layMonAnTheoMa(cthd.getMonAn().getMaMon());
                int ton = mon != null ? mon.getSoLuong() : 0;
                String msg = ton <= 0
                    ? "Món '" + cthd.getMonAn().getTenMon() + "' không quản lý tồn kho!"
                    : "Không đủ hàng! Chỉ còn " + ton + " phần.";
                JOptionPane.showMessageDialog(null, msg, "Cảnh báo tồn kho", JOptionPane.WARNING_MESSAGE);
                con.rollback();
                return false;
            }

            // Thêm chi tiết hóa đơn
            String sql = "INSERT INTO ChiTietHoaDon (maHoaDon, maMon, soLuong, donGia, thanhTien, ghiChu) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, cthd.getHoaDon().getMaHoaDon());
                stmt.setString(2, cthd.getMonAn().getMaMon());
                stmt.setInt(3, cthd.getSoLuong());
                stmt.setDouble(4, cthd.getDonGia());
                stmt.setDouble(5, cthd.getThanhTien());
                stmt.setString(6, cthd.getGhiChu() != null ? cthd.getGhiChu() : "");

                if (stmt.executeUpdate() == 0) {
                    con.rollback();
                    return false;
                }
            }

            // Cập nhật tổng tiền hóa đơn
            double tongMoi = tinhTongTienHoaDon(con, cthd.getHoaDon().getMaHoaDon());
            new HoaDonDAO().capNhatTongTien(con, cthd.getHoaDon().getMaHoaDon(), tongMoi);

            // Ghi log
            if (maNVThaoTac != null) {
                logDAO.logDonGian(con, maNVThaoTac, LoaiThaoTac.THEM, "ChiTietHoaDon",
                    cthd.getHoaDon().getMaHoaDon() + " - " + cthd.getMonAn().getMaMon(),
                    "Món: " + cthd.getMonAn().getTenMon() + " | SL: " + cthd.getSoLuong(),
                    "Thêm món vào hóa đơn");
            }

            con.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            if (con != null) {
                try { con.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(originalAutoCommit); } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }

    // ===================================================================
    // 2. XÓA MỘT CHI TIẾT (có cộng lại tồn kho + cập nhật tổng tiền + log)
    // ===================================================================
    public boolean xoaChiTietHoaDon(String maHoaDon, String maMon, String maNVThaoTac) {
        Connection con = null;
        boolean originalAutoCommit = true;

        try {
            con = ConnectDB.getConnection();
            originalAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);

            ChiTietHoaDon cthd = timChiTietTheoHoaDonVaMon(maHoaDon, maMon);
            if (cthd == null) {
                con.setAutoCommit(originalAutoCommit);
                return false;
            }

            // Cộng lại tồn kho
            new MonAnDAO().congLaiTonKho(con, maMon, cthd.getSoLuong(), maNVThaoTac);

            // Xóa chi tiết
            String sql = "DELETE FROM ChiTietHoaDon WHERE maHoaDon = ? AND maMon = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, maHoaDon);
                stmt.setString(2, maMon);
                if (stmt.executeUpdate() == 0) {
                    con.rollback();
                    return false;
                }
            }

            // Cập nhật tổng tiền
            double tongMoi = tinhTongTienHoaDon(con, maHoaDon);
            new HoaDonDAO().capNhatTongTien(con, maHoaDon, tongMoi);

            // Ghi log
            if (maNVThaoTac != null) {
                logDAO.logDonGian(con, maNVThaoTac, LoaiThaoTac.XOA, "ChiTietHoaDon",
                    maHoaDon + " - " + maMon,
                    "Xóa món: " + cthd.getMonAn().getTenMon() + " (SL: " + cthd.getSoLuong() + ")",
                    "Xóa món + hoàn tồn kho");
            }

            con.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            if (con != null) try { con.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            return false;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(originalAutoCommit); } catch (Exception ex) { ex.printStackTrace(); }
            }
        }
    }

    // ===================================================================
    // 3. CẬP NHẬT CHI TIẾT (số lượng, thành tiền, ghi chú)
    // ===================================================================
    public boolean capNhatChiTietHoaDon(ChiTietHoaDon cthdMoi, String maNVThaoTac) {
        ChiTietHoaDon cthdCu = timChiTietTheoHoaDonVaMon(
                cthdMoi.getHoaDon().getMaHoaDon(), cthdMoi.getMonAn().getMaMon());

        if (cthdCu == null) return false;

        String sql = "UPDATE ChiTietHoaDon SET soLuong = ?, thanhTien = ?, ghiChu = ? "
                   + "WHERE maHoaDon = ? AND maMon = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, cthdMoi.getSoLuong());
            stmt.setDouble(2, cthdMoi.getThanhTien());
            stmt.setString(3, cthdMoi.getGhiChu() != null ? cthdMoi.getGhiChu() : "");
            stmt.setString(4, cthdMoi.getHoaDon().getMaHoaDon());
            stmt.setString(5, cthdMoi.getMonAn().getMaMon());

            boolean ok = stmt.executeUpdate() > 0;

            if (ok && maNVThaoTac != null) {
                String thayDoi = String.format("SL: %d → %d, Thành tiền: %.0f → %.0f",
                        cthdCu.getSoLuong(), cthdMoi.getSoLuong(),
                        cthdCu.getThanhTien(), cthdMoi.getThanhTien());
                logDAO.logDonGian(maNVThaoTac, LoaiThaoTac.SUA, "ChiTietHoaDon",
                        cthdMoi.getHoaDon().getMaHoaDon() + " - " + cthdMoi.getMonAn().getMaMon(),
                        thayDoi, "Cập nhật số lượng món trong hóa đơn");
            }
            return ok;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===================================================================
    // 4. XÓA TOÀN BỘ CHI TIẾT THEO HÓA ĐƠN
    // ===================================================================
    public boolean xoaChiTietTheoMaHD(String maHoaDon, String maNVThaoTac) {
        List<ChiTietHoaDon> dsCTHD = layDanhSachTheoHoaDon(maHoaDon);

        String sql = "DELETE FROM ChiTietHoaDon WHERE maHoaDon = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maHoaDon);
            boolean ok = stmt.executeUpdate() > 0;

            if (ok && maNVThaoTac != null && !dsCTHD.isEmpty()) {
                logDAO.logDonGian(maNVThaoTac, LoaiThaoTac.XOA, "ChiTietHoaDon",
                        maHoaDon, "Xóa " + dsCTHD.size() + " món",
                        "Xóa toàn bộ chi tiết hóa đơn " + maHoaDon);
            }
            return ok;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===================================================================
    // 5. TÌM CHI TIẾT THEO HÓA ĐƠN + MÓN
    // ===================================================================
    public ChiTietHoaDon timChiTietTheoHoaDonVaMon(String maHoaDon, String maMon) {
        String sql = "SELECT cthd.*, ma.tenMon, ma.gia FROM ChiTietHoaDon cthd "
                   + "JOIN MonAn ma ON cthd.maMon = ma.maMon "
                   + "WHERE cthd.maHoaDon = ? AND cthd.maMon = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maHoaDon);
            stmt.setString(2, maMon);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToChiTietHoaDon(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ===================================================================
    // 6. LẤY DANH SÁCH CHI TIẾT THEO HÓA ĐƠN (có tên món + giá)
    // ===================================================================
    public List<ChiTietHoaDon> layDanhSachTheoHoaDon(String maHoaDon) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT cthd.*, ma.tenMon, ma.gia "
                   + "FROM ChiTietHoaDon cthd "
                   + "INNER JOIN MonAn ma ON cthd.maMon = ma.maMon "
                   + "WHERE cthd.maHoaDon = ? ORDER BY ma.tenMon";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maHoaDon);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToChiTietHoaDon(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ===================================================================
    // 7. TÍNH TỔNG TIỀN HÓA ĐƠN (dùng trong transaction)
    // ===================================================================
    private double tinhTongTienHoaDon(Connection con, String maHD) throws SQLException {
        String sql = "SELECT SUM(thanhTien) FROM ChiTietHoaDon WHERE maHoaDon = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maHD);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getDouble(1) : 0;
            }
        }
    }

    // Giữ lại method cũ để tương thích
    public double tinhTongTienHoaDon(String maHD) {
        try (Connection con = ConnectDB.getConnection()) {
            return tinhTongTienHoaDon(con, maHD);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ===================================================================
    // 8. GỘP HÓA ĐƠN (gộp bàn) - ĐẦY ĐỦ XỬ LÝ TIỀN CỌC PHIẾU ĐẶT BÀN
    // ===================================================================
    public boolean gopHoaDon(String maHDCu, String maHDDich, String maBanNguon, String maBanDich) {
        Connection con = null;
        try {
            con = ConnectDB.getConnection();
            con.setAutoCommit(false);

            // 1. Cộng món trùng
            String sqlCong = """
                UPDATE ChiTietHoaDon ctd_dich
                SET soLuong = ctd_dich.soLuong + ctd_nguon.soLuong,
                    thanhTien = (ctd_dich.soLuong + ctd_nguon.soLuong) * ctd_dich.donGia
                FROM ChiTietHoaDon ctd_nguon
                WHERE ctd_dich.maMon = ctd_nguon.maMon
                  AND ctd_dich.maHoaDon = ?
                  AND ctd_nguon.maHoaDon = ?
                """;
            try (PreparedStatement ps = con.prepareStatement(sqlCong)) {
                ps.setString(1, maHDDich);
                ps.setString(2, maHDCu);
                ps.executeUpdate();
            }

            // 2. Thêm món chưa có ở đích
            String sqlThem = """
                INSERT INTO ChiTietHoaDon (maHoaDon, maMon, soLuong, donGia, thanhTien, ghiChu)
                SELECT ?, maMon, soLuong, donGia, soLuong * donGia, ghiChu
                FROM ChiTietHoaDon
                WHERE maHoaDon = ?
                  AND maMon NOT IN (SELECT maMon FROM ChiTietHoaDon WHERE maHoaDon = ?)
                """;
            try (PreparedStatement ps = con.prepareStatement(sqlThem)) {
                ps.setString(1, maHDDich);
                ps.setString(2, maHDCu);
                ps.setString(3, maHDDich);
                ps.executeUpdate();
            }

            // 3. Xử lý tiền cọc phiếu đặt bàn
            String sqlPD = "SELECT maPhieuDat, soTienCoc FROM PhieuDatBan WHERE maBan = ?";
            String maPhieuNguon = null, maPhieuDich = null;
            double cocNguon = 0, cocDich = 0;

            try (PreparedStatement ps = con.prepareStatement(sqlPD)) {
                // Nguồn
                ps.setString(1, maBanNguon);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        maPhieuNguon = rs.getString("maPhieuDat");
                        cocNguon = rs.getDouble("soTienCoc");
                    }
                }
                // Đích
                ps.setString(1, maBanDich);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        maPhieuDich = rs.getString("maPhieuDat");
                        cocDich = rs.getDouble("soTienCoc");
                    }
                }
            }

            double tongCoc = cocNguon + cocDich;

            if (maPhieuDich != null) {
                // Cộng cọc vào phiếu đích
                String sqlUp = "UPDATE PhieuDatBan SET soTienCoc = ? WHERE maPhieuDat = ?";
                try (PreparedStatement ps = con.prepareStatement(sqlUp)) {
                    ps.setDouble(1, tongCoc);
                    ps.setString(2, maPhieuDich);
                    ps.executeUpdate();
                }
                // Xóa phiếu nguồn nếu có
                if (maPhieuNguon != null) {
                    String sqlDel = "DELETE FROM PhieuDatBan WHERE maPhieuDat = ?";
                    try (PreparedStatement ps = con.prepareStatement(sqlDel)) {
                        ps.setString(1, maPhieuNguon);
                        ps.executeUpdate();
                    }
                }
            } else if (maPhieuNguon != null) {
                // Chuyển phiếu nguồn sang bàn đích
                String sqlMove = "UPDATE PhieuDatBan SET maBan = ? WHERE maPhieuDat = ?";
                try (PreparedStatement ps = con.prepareStatement(sqlMove)) {
                    ps.setString(1, maBanDich);
                    ps.setString(2, maPhieuNguon);
                    ps.executeUpdate();
                }
            }

            // 4. Xóa chi tiết + hóa đơn nguồn
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM ChiTietHoaDon WHERE maHoaDon = ?")) {
                ps.setString(1, maHDCu);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM HoaDon WHERE maHoaDon = ?")) {
                ps.setString(1, maHDCu);
                ps.executeUpdate();
            }

            con.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            if (con != null) {
                try { con.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }

    // ===================================================================
    // HELPER: Map ResultSet → ChiTietHoaDon
    // ===================================================================
    private ChiTietHoaDon mapResultSetToChiTietHoaDon(ResultSet rs) throws SQLException {
        ChiTietHoaDon cthd = new ChiTietHoaDon();

        HoaDon hd = new HoaDon();
        hd.setMaHoaDon(rs.getString("maHoaDon"));
        cthd.setHoaDon(hd);

        MonAn ma = new MonAn();
        ma.setMaMon(rs.getString("maMon"));
        ma.setTenMon(rs.getString("tenMon"));
        ma.setGia(rs.getDouble("gia"));
        cthd.setMonAn(ma);

        cthd.setSoLuong(rs.getInt("soLuong"));
        cthd.setDonGia(rs.getDouble("donGia"));
        cthd.setThanhTien(rs.getDouble("thanhTien"));
        cthd.setGhiChu(rs.getString("ghiChu"));

        return cthd;
    }
}