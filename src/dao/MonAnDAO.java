package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.MonAn;
import entity.NhatKyThaoTac.LoaiThaoTac;

public class MonAnDAO {
	
	private NhatKyThaoTacDAO logDAO = NhatKyThaoTacDAO.getInstance();
	
    public MonAn layMonAnTheoMa(String maMonAn) {
    	Connection con = ConnectDB.getConnection();
        String sql = "select * from MonAn where maMon=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maMonAn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MonAn(rs.getString("maMon"),
                        rs.getString("tenMon"),
                        rs.getDouble("gia"),
                        rs.getString("donViTinh"),
                        rs.getBoolean("trangThai"),
                        rs.getString("hinhAnh"),
                        rs.getInt("soLuong"),
                        rs.getString("moTa"),
                        rs.getString("maLoaiMon")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MonAn> getAllMonAn() {
        List<MonAn> dsMonAn = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM MonAn";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                dsMonAn.add(new MonAn(
                        rs.getString("maMon"),
                        rs.getString("tenMon"),
                        rs.getDouble("gia"),
                        rs.getString("donViTinh"),
                        rs.getBoolean("trangThai"),
                        rs.getString("hinhAnh"),
                        rs.getInt("soLuong"),
                        rs.getString("moTa"),
                        rs.getString("maLoaiMon")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsMonAn;
    }

    public String chuyenDoiLoaiSangMaLoai(String tenLoai) {
        if (tenLoai.equalsIgnoreCase("Món chính"))
            return "MC";
        if (tenLoai.equalsIgnoreCase("Món ăn kèm")) {
            return "MK";
        }
        else return "DO";
    }

    public String chuyenDoiMaLoaiSangTen(String maLoai) {
        if (maLoai.equalsIgnoreCase("MC"))
            return "Món chính";
        if (maLoai.equalsIgnoreCase("DO")) {
            return "Đồ uống";
        }
        else return "Món ăn kèm";
    }

 // ========== THÊM MÓN MỚI - HỖ TRỢ LƯU NULL CHO SỐ LƯỢNG ==========
    public boolean themMonMoi(MonAn mon, String maNVThaoTac) {
        Connection con = ConnectDB.getConnection();
        String sql = "INSERT INTO MonAn (maMon, tenMon, gia, donViTinh, trangThai, hinhAnh, soLuong, moTa, maLoaiMon) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, mon.getMaMon());
            stmt.setString(2, mon.getTenMon());
            stmt.setDouble(3, mon.getGia());
            stmt.setString(4, mon.getDonViTinh());
            stmt.setBoolean(5, mon.isTrangThai());
            stmt.setString(6, mon.getHinhAnh());
            
            // Xử lý soLuong: nếu = 0 và không có giá trị thực → coi như NULL
            if (mon.getSoLuong() == 0) {
                stmt.setNull(7, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(7, mon.getSoLuong());
            }
            
            stmt.setString(8, mon.getMoTa());
            stmt.setString(9, mon.getLoaiMon());

            boolean result = stmt.executeUpdate() > 0;

            if (result && maNVThaoTac != null) {
                logDAO.logThem(maNVThaoTac, mon, "Thêm món ăn mới");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ========== SỬA MÓN ĂN - HỖ TRỢ LƯU NULL CHO SỐ LƯỢNG ==========
    public boolean suaMonAn(MonAn mon, String maNVThaoTac) {
        MonAn monCu = layMonAnTheoMa(mon.getMaMon());
        if (monCu == null) return false;

        String sql = "UPDATE MonAn SET tenMon = ?, gia = ?, donViTinh = ?, trangThai = ?, hinhAnh = ?, soLuong = ?, moTa = ?, maLoaiMon = ? WHERE maMon = ?";
        Connection con = ConnectDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, mon.getTenMon());
            stmt.setDouble(2, mon.getGia());
            stmt.setString(3, mon.getDonViTinh());
            stmt.setBoolean(4, mon.isTrangThai());
            stmt.setString(5, mon.getHinhAnh());

            // Xử lý soLuong: nếu = 0 → coi như người dùng muốn xóa số lượng → lưu NULL
            if (mon.getSoLuong() == 0) {
                stmt.setNull(6, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(6, mon.getSoLuong());
            }

            stmt.setString(7, mon.getMoTa());
            stmt.setString(8, mon.getLoaiMon());
            stmt.setString(9, mon.getMaMon());

            boolean result = stmt.executeUpdate() > 0;

            if (result && maNVThaoTac != null && monCu != null) {
                logDAO.logSua(maNVThaoTac, monCu, mon, "Cập nhật thông tin món ăn");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ========== ẨN MÓN ĂN - CÓ LOGGING ==========
    public boolean anMonAn(String maMon, String maNVThaoTac) throws SQLException {
        // Lấy dữ liệu cũ
        MonAn monCu = layMonAnTheoMa(maMon);
        
        String sql = "UPDATE MonAn SET trangThai = 0 WHERE maMon = ?";
        Connection con = ConnectDB.getConnection();
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, maMon);
            boolean result = pst.executeUpdate() > 0;
            
            // GHI LOG nếu thành công
            if (result && maNVThaoTac != null && monCu != null) {
                logDAO.logDonGian(
                    maNVThaoTac, 
                    LoaiThaoTac.SUA, 
                    "MonAn", 
                    maMon,
                    "Trạng thái: Hoạt động -> Ngừng bán",
                    "Ẩn món ăn (ngừng bán)"
                );
            }
            
            return result;
        }
    }

    public ArrayList<String> layRaLoaiMonAn() throws SQLException {
        ArrayList<String> dsTenLoaiMonAn = new ArrayList<>();
        String sql = "SELECT tenLoaiMon FROM LoaiMonAn";
        Connection con = ConnectDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                dsTenLoaiMonAn.add(rs.getString("tenLoaiMon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsTenLoaiMonAn;
    }

    public ArrayList<String> layRaDonViTinh() {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT donViTinh FROM MonAn WHERE donViTinh IS NOT NULL";
        Connection con = ConnectDB.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("donViTinh"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String layMaMonLonNhatTheoLoai(String maLoai) {
    	Connection con = ConnectDB.getConnection();
        String sql = "SELECT TOP 1 maMon FROM MonAn WHERE maMon LIKE ? ORDER BY maMon DESC";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maLoai + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("maMon");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean themLoaiMon(String maLoai, String tenLoai) throws SQLException {
    	Connection con = ConnectDB.getConnection();
        String sql = "INSERT INTO LoaiMonAn (maLoaiMon, tenLoaiMon) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maLoai);
            ps.setString(2, tenLoai);
            return ps.executeUpdate() > 0;
        }
    }
    
    public String chuyenMaLoaiSangTen(String maLoai) throws SQLException {
    	Connection con = ConnectDB.getConnection();
        String sql = "SELECT tenLoaiMon FROM LoaiMonAn WHERE maLoaiMon = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maLoai);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("tenLoaiMon");
            }
        }
        return maLoai; 
    }

    public String chuyenTenLoaiSangMa(String tenLoai) throws SQLException {
    	Connection con = ConnectDB.getConnection();
        String sql = "SELECT maLoaiMon FROM LoaiMonAn WHERE tenLoaiMon = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenLoai);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("maLoaiMon");
            }
        }
        return tenLoai; // fallback
    }
    
    // ============================================================
    // ✅ TRỪ TỒN KHO - OVERLOAD NHẬN CONNECTION (CHO TRANSACTION)
    // ============================================================
    public boolean truTonKho(Connection con, String maMon, int soLuongTru, String maNVThaoTac) {
        MonAn mon = layMonAnTheoMa(maMon);
        if (mon == null) return false;

        // Nếu soLuong == 0 → món KHÔNG quản lý tồn kho → cho đặt thoải mái
        if (mon.getSoLuong() <= 0) {
            if (maNVThaoTac != null) {
                logDAO.logDonGian(con, maNVThaoTac, LoaiThaoTac.SUA, "MonAn", maMon,
                    "Không kiểm tra tồn (soLuong = 0) → cho phép đặt " + soLuongTru + " phần",
                    "Đặt món không giới hạn tồn kho (mì cay, combo, ...)");
            }
            return true; // Cho phép đặt
        }

        // Nếu soLuong > 0 → có quản lý tồn → phải kiểm tra đủ hàng
        if (mon.getSoLuong() < soLuongTru) {
            return false; // Không đủ hàng
        }

        String sql = "UPDATE MonAn SET soLuong = soLuong - ? WHERE maMon = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuongTru);
            ps.setString(2, maMon);

            boolean result = ps.executeUpdate() > 0;

            if (result && maNVThaoTac != null) {
                logDAO.logDonGian(con, maNVThaoTac, LoaiThaoTac.SUA, "MonAn", maMon,
                    String.format("Tồn kho: %d → %d (-%d)", mon.getSoLuong(), mon.getSoLuong() - soLuongTru, soLuongTru),
                    "Trừ tồn kho khi đặt món");
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ TRỪ TỒN KHO - METHOD CŨ (GIỮ LẠI ĐỂ TƯƠNG THÍCH)
    public boolean truTonKho(String maMon, int soLuongTru, String maNVThaoTac) {
        MonAn mon = layMonAnTheoMa(maMon);
        if (mon == null) return false;

        // Nếu soLuong == 0 → món KHÔNG quản lý tồn kho → cho đặt thoải mái
        if (mon.getSoLuong() <= 0) {
            if (maNVThaoTac != null) {
                logDAO.logDonGian(maNVThaoTac, LoaiThaoTac.SUA, "MonAn", maMon,
                    "Không kiểm tra tồn (soLuong = 0) → cho phép đặt " + soLuongTru + " phần",
                    "Đặt món không giới hạn tồn kho (mì cay, combo, ...)");
            }
            return true; // Cho phép đặt
        }

        // Nếu soLuong > 0 → có quản lý tồn → phải kiểm tra đủ hàng
        if (mon.getSoLuong() < soLuongTru) {
            return false; // Không đủ hàng
        }

        String sql = "UPDATE MonAn SET soLuong = soLuong - ? WHERE maMon = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuongTru);
            ps.setString(2, maMon);

            boolean result = ps.executeUpdate() > 0;

            if (result && maNVThaoTac != null) {
                logDAO.logDonGian(maNVThaoTac, LoaiThaoTac.SUA, "MonAn", maMon,
                    String.format("Tồn kho: %d → %d (-%d)", mon.getSoLuong(), mon.getSoLuong() - soLuongTru, soLuongTru),
                    "Trừ tồn kho khi đặt món");
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ============================================================
    // ✅ CỘNG LẠI TỒN KHO - OVERLOAD NHẬN CONNECTION (CHO TRANSACTION)
    // ============================================================
    public boolean congLaiTonKho(Connection con, String maMon, int soLuongCong, String maNVThaoTac) {
        MonAn mon = layMonAnTheoMa(maMon);
        if (mon == null) return false;

        // Nếu soLuong ban đầu <= 0 → không quản lý tồn → không cần cộng lại
        if (mon.getSoLuong() <= 0) {
            return true; // Vẫn cho phép xóa
        }

        String sql = "UPDATE MonAn SET soLuong = soLuong + ? WHERE maMon = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuongCong);
            ps.setString(2, maMon);

            boolean result = ps.executeUpdate() > 0;

            if (result && maNVThaoTac != null) {
                int tonMoi = mon.getSoLuong() + soLuongCong;
                logDAO.logDonGian(con, maNVThaoTac, LoaiThaoTac.SUA, "MonAn", maMon,
                    String.format("Tồn kho: %d → %d (+%d)", mon.getSoLuong(), tonMoi, soLuongCong),
                    "Hoàn tồn kho khi xóa món khỏi hóa đơn");
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ CỘNG LẠI TỒN KHO - METHOD CŨ (GIỮ LẠI ĐỂ TƯƠNG THÍCH)
    public boolean congLaiTonKho(String maMon, int soLuongCong, String maNVThaoTac) {
        MonAn mon = layMonAnTheoMa(maMon);
        if (mon == null) return false;

        // Nếu soLuong ban đầu <= 0 → không quản lý tồn → không cần cộng lại
        if (mon.getSoLuong() <= 0) {
            return true; // Vẫn cho phép xóa
        }

        String sql = "UPDATE MonAn SET soLuong = soLuong + ? WHERE maMon = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuongCong);
            ps.setString(2, maMon);

            boolean result = ps.executeUpdate() > 0;

            if (result && maNVThaoTac != null) {
                int tonMoi = mon.getSoLuong() + soLuongCong;
                logDAO.logDonGian(maNVThaoTac, LoaiThaoTac.SUA, "MonAn", maMon,
                    String.format("Tồn kho: %d → %d (+%d)", mon.getSoLuong(), tonMoi, soLuongCong),
                    "Hoàn tồn kho khi xóa món khỏi hóa đơn");
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // === THÊM PHƯƠNG THỨC KIỂM TRA TRÙNG TÊN MÓN TRONG MonAnDAO ===
    public boolean kiemTraTenMonTrung(String tenMon, String maMonHienTai) throws SQLException {
        String sql = "SELECT COUNT(*) FROM MonAn WHERE tenMon = ? AND trangThai = 1";
        if (maMonHienTai != null && !maMonHienTai.trim().isEmpty()) {
            sql += " AND maMon != ?";
        }
        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
            ps.setString(1, tenMon);
            if (maMonHienTai != null && !maMonHienTai.trim().isEmpty()) {
                ps.setString(2, maMonHienTai);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}