package dao;

import entity.*;
import entity.NhatKyThaoTac.LoaiThaoTac;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import connectDB.ConnectDB;

public class ChiTietHoaDonDAO {
    private static ChiTietHoaDonDAO instance;
    private NhatKyThaoTacDAO logDAO = NhatKyThaoTacDAO.getInstance();

    public static ChiTietHoaDonDAO getInstance() {
        if (instance == null) {
            instance = new ChiTietHoaDonDAO();
        }
        return instance;
    }

    public boolean themChiTietHoaDon(ChiTietHoaDon cthd, String maNVThaoTac) {
        Connection con = null;
        boolean originalAutoCommit = true;
        
        try {
            // Lấy connection
            con = ConnectDB.getConnection();
            if (con == null || con.isClosed()) {
                System.err.println("❌ Connection is null or closed, attempting to reconnect...");
                ConnectDB.getInstance().connect();
                con = ConnectDB.getConnection();
            }
            
            // Lưu trạng thái autoCommit ban đầu
            originalAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);

            MonAnDAO monAnDAO = new MonAnDAO();

            // ✅ TRUYỀN CONNECTION VÀO - QUAN TRỌNG!
            if (!monAnDAO.truTonKho(con, cthd.getMonAn().getMaMon(), cthd.getSoLuong(), maNVThaoTac)) {
                MonAn mon = monAnDAO.layMonAnTheoMa(cthd.getMonAn().getMaMon());
                int ton = mon != null ? mon.getSoLuong() : 0;

                String msg = ton <= 0
                    ? "Món '" + cthd.getMonAn().getTenMon() + "' không quản lý tồn kho!"
                    : "Không đủ hàng! Chỉ còn " + ton + " phần.";

                JOptionPane.showMessageDialog(null, msg, "Cảnh báo tồn kho", JOptionPane.WARNING_MESSAGE);
                con.rollback();
                con.setAutoCommit(originalAutoCommit);
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
                    con.setAutoCommit(originalAutoCommit);
                    return false;
                }
            }

            // Cập nhật tổng tiền - TRUYỀN CONNECTION
            double tongMoi = tinhTongTienHoaDon(con, cthd.getHoaDon().getMaHoaDon());
            HoaDonDAO hoaDonDAO = new HoaDonDAO();
            hoaDonDAO.capNhatTongTien(con, cthd.getHoaDon().getMaHoaDon(), tongMoi);

            // Ghi log - TRUYỀN CONNECTION
            if (maNVThaoTac != null) {
                logDAO.logDonGian(con, maNVThaoTac, LoaiThaoTac.THEM, "ChiTietHoaDon",
                    cthd.getHoaDon().getMaHoaDon() + " - " + cthd.getMonAn().getMaMon(),
                    "Món: " + cthd.getMonAn().getTenMon() + " | SL: " + cthd.getSoLuong(),
                    "Thêm món vào hóa đơn");
            }

            con.commit();
            con.setAutoCommit(originalAutoCommit);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null && !con.isClosed()) {
                    con.rollback();
                    con.setAutoCommit(originalAutoCommit);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
        // ❌ KHÔNG ĐÓNG CONNECTION - để ConnectDB quản lý
    }

    public boolean xoaChiTietHoaDon(String maHoaDon, String maMon, String maNVThaoTac) {
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

            ChiTietHoaDon cthd = timChiTietTheoHoaDonVaMon(maHoaDon, maMon);
            if (cthd == null) {
                con.setAutoCommit(originalAutoCommit);
                return false;
            }

            // ✅ TRUYỀN CONNECTION
            MonAnDAO monAnDAO = new MonAnDAO();
            monAnDAO.congLaiTonKho(con, maMon, cthd.getSoLuong(), maNVThaoTac);

            String sql = "DELETE FROM ChiTietHoaDon WHERE maHoaDon = ? AND maMon = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, maHoaDon);
                stmt.setString(2, maMon);
                if (stmt.executeUpdate() == 0) {
                    con.rollback();
                    con.setAutoCommit(originalAutoCommit);
                    return false;
                }
            }

            // ✅ TRUYỀN CONNECTION
            double tongMoi = tinhTongTienHoaDon(con, maHoaDon);
            HoaDonDAO hoaDonDAO = new HoaDonDAO();
            hoaDonDAO.capNhatTongTien(con, maHoaDon, tongMoi);

            if (maNVThaoTac != null) {
                logDAO.logDonGian(con, maNVThaoTac, LoaiThaoTac.XOA, "ChiTietHoaDon",
                    maHoaDon + " - " + maMon,
                    "Xóa món: " + cthd.getMonAn().getTenMon() + " (SL: " + cthd.getSoLuong() + ")",
                    "Xóa món + hoàn tồn kho");
            }

            con.commit();
            con.setAutoCommit(originalAutoCommit);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null && !con.isClosed()) {
                    con.rollback();
                    con.setAutoCommit(originalAutoCommit);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    public boolean capNhatChiTietHoaDon(ChiTietHoaDon cthdMoi, String maNVThaoTac) {
        ChiTietHoaDon cthdCu = timChiTietTheoHoaDonVaMon(
                cthdMoi.getHoaDon().getMaHoaDon(), cthdMoi.getMonAn().getMaMon());

        String sql = "UPDATE ChiTietHoaDon SET soLuong = ?, thanhTien = ?, ghiChu = ? "
                   + "WHERE maHoaDon = ? AND maMon = ?";
        Connection con = ConnectDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, cthdMoi.getSoLuong());
            stmt.setDouble(2, cthdMoi.getThanhTien());
            stmt.setString(3, cthdMoi.getGhiChu() != null ? cthdMoi.getGhiChu() : "");
            stmt.setString(4, cthdMoi.getHoaDon().getMaHoaDon());
            stmt.setString(5, cthdMoi.getMonAn().getMaMon());

            boolean result = stmt.executeUpdate() > 0;

            if (result && maNVThaoTac != null && cthdCu != null) {
                String cu = String.format("SL: %d → %d, Thành tiền: %.0f → %.0f",
                        cthdCu.getSoLuong(), cthdMoi.getSoLuong(),
                        cthdCu.getThanhTien(), cthdMoi.getThanhTien());

                logDAO.logDonGian(maNVThaoTac, LoaiThaoTac.SUA, "ChiTietHoaDon",
                        cthdMoi.getHoaDon().getMaHoaDon() + " - " + cthdMoi.getMonAn().getMaMon(),
                        cu, "Cập nhật số lượng món trong hóa đơn");
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaChiTietTheoMaHD(String maHoaDon, String maNVThaoTac) {
        List<ChiTietHoaDon> dsCTHD = layDanhSachTheoHoaDon(maHoaDon);

        String sql = "DELETE FROM ChiTietHoaDon WHERE maHoaDon = ?";
        Connection con = ConnectDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maHoaDon);
            boolean result = stmt.executeUpdate() > 0;

            if (result && maNVThaoTac != null && !dsCTHD.isEmpty()) {
                logDAO.logDonGian(maNVThaoTac, LoaiThaoTac.XOA, "ChiTietHoaDon",
                        maHoaDon, "Xóa " + dsCTHD.size() + " món",
                        "Xóa toàn bộ chi tiết hóa đơn " + maHoaDon);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ChiTietHoaDon timChiTietTheoHoaDonVaMon(String maHoaDon, String maMon) {
        String sql = "SELECT cthd.*, ma.tenMon, ma.gia FROM ChiTietHoaDon cthd "
                   + "JOIN MonAn ma ON cthd.maMon = ma.maMon "
                   + "WHERE cthd.maHoaDon = ? AND cthd.maMon = ?";
        Connection con = ConnectDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maHoaDon);
            stmt.setString(2, maMon);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToChiTietHoaDon(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ChiTietHoaDon> layDanhSachTheoHoaDon(String maHoaDon) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT cthd.*, ma.tenMon, ma.gia "
                   + "FROM ChiTietHoaDon cthd "
                   + "INNER JOIN MonAn ma ON cthd.maMon = ma.maMon "
                   + "WHERE cthd.maHoaDon = ? ORDER BY ma.tenMon";
        Connection con = ConnectDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maHoaDon);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToChiTietHoaDon(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ THÊM OVERLOAD NHẬN CONNECTION
    private double tinhTongTienHoaDon(Connection con, String maHD) throws SQLException {
        String sql = "SELECT SUM(thanhTien) FROM ChiTietHoaDon WHERE maHoaDon = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0;
    }

    // ✅ GIỮ LẠI METHOD CŨ ĐỂ TƯƠNG THÍCH
    public double tinhTongTienHoaDon(String maHD) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT SUM(thanhTien) FROM ChiTietHoaDon WHERE maHoaDon = ?")) {
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean gopHoaDon(String maHDCu, String maHDDich, String maBanNguon, String maBanDich, String maNVThaoTac) {
        Connection con = ConnectDB.getConnection();
        NhatKyThaoTacDAO logDAO = NhatKyThaoTacDAO.getInstance();

        try {
            con.setAutoCommit(false);

            String sqlCong = """
                UPDATE ctd_dich 
                SET ctd_dich.soLuong = ctd_dich.soLuong + ctd_nguon.soLuong,
                    ctd_dich.thanhTien = ctd_dich.thanhTien + ctd_nguon.thanhTien
                FROM ChiTietHoaDon ctd_dich
                INNER JOIN ChiTietHoaDon ctd_nguon ON ctd_dich.maMon = ctd_nguon.maMon
                WHERE ctd_dich.maHoaDon = ? AND ctd_nguon.maHoaDon = ?
                """;

            try (PreparedStatement ps = con.prepareStatement(sqlCong)) {
                ps.setString(1, maHDDich);
                ps.setString(2, maHDCu);
                ps.executeUpdate();
            }

            String sqlThem = """
                INSERT INTO ChiTietHoaDon (maHoaDon, maMon, soLuong, donGia, thanhTien, ghiChu)
                SELECT ?, maMon, soLuong, donGia, thanhTien, ISNULL(ghiChu, '')
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

            String sqlXoaCT = "DELETE FROM ChiTietHoaDon WHERE maHoaDon = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlXoaCT)) {
                ps.setString(1, maHDCu);
                ps.executeUpdate();
            }

            String sqlXoaHD = "DELETE FROM HoaDon WHERE maHoaDon = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlXoaHD)) {
                ps.setString(1, maHDCu);
                ps.executeUpdate();
            }

            con.commit();

            if (maNVThaoTac != null) {
                String chiTiet = String.format("Gộp từ bàn %s (HD: %s) → bàn %s (HD: %s)",
                        maBanNguon, maHDCu, maBanDich, maHDDich);

                logDAO.logDonGian(
                    maNVThaoTac,
                    LoaiThaoTac.SUA,
                    "HoaDon",
                    maHDCu + " → " + maHDDich,
                    chiTiet,
                    "Gộp bàn: " + maBanNguon + " → " + maBanDich
                );
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

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