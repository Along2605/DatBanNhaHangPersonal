package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import connectDB.ConnectDB;
import entity.Loggable;
import entity.NhatKyThaoTac;
import entity.NhatKyThaoTac.LoaiThaoTac;

public class NhatKyThaoTacDAO {
    
    // ===== SINGLETON PATTERN =====
    private static NhatKyThaoTacDAO instance;
    
    public static NhatKyThaoTacDAO getInstance() {
        if (instance == null) {
            instance = new NhatKyThaoTacDAO();
        }
        return instance;
    }
    
    // ============================================================
    // ✅ GHI LOG CHUNG - OVERLOAD NHẬN CONNECTION (CHO TRANSACTION)
    // ============================================================
    public boolean ghiLog(Connection con, NhatKyThaoTac log) {
        String sql = "INSERT INTO NhatKyThaoTac (maNV, loaiThaoTac, tenBang, " +
                     "maDoiTuong, noiDungCu, noiDungMoi, thoiGian, ghiChu) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, log.getMaNV());
            ps.setString(2, log.getLoaiThaoTac().getValue());
            ps.setString(3, log.getTenBang());
            ps.setString(4, log.getMaDoiTuong());
            ps.setString(5, log.getNoiDungCu());
            ps.setString(6, log.getNoiDungMoi());
            ps.setTimestamp(7, Timestamp.valueOf(log.getThoiGian()));
            ps.setString(8, log.getGhiChu());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // ✅ GHI LOG CHUNG - METHOD CŨ (GIỮ LẠI ĐỂ TƯƠNG THÍCH)
    public boolean ghiLog(NhatKyThaoTac log) {
        String sql = "INSERT INTO NhatKyThaoTac (maNV, loaiThaoTac, tenBang, " +
                     "maDoiTuong, noiDungCu, noiDungMoi, thoiGian, ghiChu) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, log.getMaNV());
            ps.setString(2, log.getLoaiThaoTac().getValue());
            ps.setString(3, log.getTenBang());
            ps.setString(4, log.getMaDoiTuong());
            ps.setString(5, log.getNoiDungCu());
            ps.setString(6, log.getNoiDungMoi());
            ps.setTimestamp(7, Timestamp.valueOf(log.getThoiGian()));
            ps.setString(8, log.getGhiChu());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // ============================================================
    // ✅ LOG THÊM - OVERLOAD NHẬN CONNECTION
    // ============================================================
    public void logThem(Connection con, String maNV, Loggable doiTuong, String ghiChu) {
        NhatKyThaoTac log = new NhatKyThaoTac(
            maNV, LoaiThaoTac.THEM,
            doiTuong.getTenBang(), doiTuong.getMaDoiTuong()
        );
        log.setNoiDungMoi(doiTuong.toLogString());
        log.setGhiChu(ghiChu);
        ghiLog(con, log);
    }
    
    // ✅ LOG THÊM - METHOD CŨ
    public void logThem(String maNV, Loggable doiTuong, String ghiChu) {
        NhatKyThaoTac log = new NhatKyThaoTac(
            maNV, LoaiThaoTac.THEM,
            doiTuong.getTenBang(), doiTuong.getMaDoiTuong()
        );
        log.setNoiDungMoi(doiTuong.toLogString());
        log.setGhiChu(ghiChu);
        ghiLog(log);
    }
    
    // ============================================================
    // ✅ LOG SỬA - OVERLOAD NHẬN CONNECTION
    // ============================================================
    public void logSua(Connection con, String maNV, Loggable doiTuongCu, 
                       Loggable doiTuongMoi, String ghiChu) {
        NhatKyThaoTac log = new NhatKyThaoTac(
            maNV, LoaiThaoTac.SUA,
            doiTuongMoi.getTenBang(), doiTuongMoi.getMaDoiTuong()
        );
        log.setNoiDungCu(doiTuongCu.toLogString());
        log.setNoiDungMoi(doiTuongMoi.toLogString());
        log.setGhiChu(ghiChu);
        ghiLog(con, log);
    }
    
    // ✅ LOG SỬA - METHOD CŨ
    public void logSua(String maNV, Loggable doiTuongCu, 
                       Loggable doiTuongMoi, String ghiChu) {
        NhatKyThaoTac log = new NhatKyThaoTac(
            maNV, LoaiThaoTac.SUA,
            doiTuongMoi.getTenBang(), doiTuongMoi.getMaDoiTuong()
        );
        log.setNoiDungCu(doiTuongCu.toLogString());
        log.setNoiDungMoi(doiTuongMoi.toLogString());
        log.setGhiChu(ghiChu);
        ghiLog(log);
    }
    
    // ============================================================
    // ✅ LOG XÓA - OVERLOAD NHẬN CONNECTION
    // ============================================================
    public void logXoa(Connection con, String maNV, Loggable doiTuong, String ghiChu) {
        NhatKyThaoTac log = new NhatKyThaoTac(
            maNV, LoaiThaoTac.XOA,
            doiTuong.getTenBang(), doiTuong.getMaDoiTuong()
        );
        log.setNoiDungCu(doiTuong.toLogString());
        log.setGhiChu(ghiChu);
        ghiLog(con, log);
    }
    
    // ✅ LOG XÓA - METHOD CŨ
    public void logXoa(String maNV, Loggable doiTuong, String ghiChu) {
        NhatKyThaoTac log = new NhatKyThaoTac(
            maNV, LoaiThaoTac.XOA,
            doiTuong.getTenBang(), doiTuong.getMaDoiTuong()
        );
        log.setNoiDungCu(doiTuong.toLogString());
        log.setGhiChu(ghiChu);
        ghiLog(log);
    }
    
    // ============================================================
    // ✅ LOG ĐƠN GIẢN - OVERLOAD NHẬN CONNECTION (QUAN TRỌNG!)
    // ============================================================
    public void logDonGian(Connection con, String maNV, LoaiThaoTac loai, String tenBang,
                           String maDoiTuong, String noiDung, String ghiChu) {
        NhatKyThaoTac log = new NhatKyThaoTac(maNV, loai, tenBang, maDoiTuong);
        if (loai == LoaiThaoTac.THEM || loai == LoaiThaoTac.SUA) {
            log.setNoiDungMoi(noiDung);
        } else {
            log.setNoiDungCu(noiDung);
        }
        log.setGhiChu(ghiChu);
        ghiLog(con, log);
    }
    
    // ✅ LOG ĐƠN GIẢN - METHOD CŨ (GIỮ LẠI ĐỂ TƯƠNG THÍCH)
    public void logDonGian(String maNV, LoaiThaoTac loai, String tenBang,
                           String maDoiTuong, String noiDung, String ghiChu) {
        NhatKyThaoTac log = new NhatKyThaoTac(maNV, loai, tenBang, maDoiTuong);
        if (loai == LoaiThaoTac.THEM || loai == LoaiThaoTac.SUA) {
            log.setNoiDungMoi(noiDung);
        } else {
            log.setNoiDungCu(noiDung);
        }
        log.setGhiChu(ghiChu);
        ghiLog(log);
    }
    
    // ===== TÌM KIẾM LOG =====
    
    public List<NhatKyThaoTac> timTheoNhanVien(String maNV) {
        return timKiem("maNV = ?", maNV);
    }
    
    public List<NhatKyThaoTac> timTheoDoiTuong(String tenBang, String maDoiTuong) {
        List<NhatKyThaoTac> ds = new ArrayList<>();
        String sql = "SELECT * FROM NhatKyThaoTac WHERE tenBang = ? AND maDoiTuong = ? " +
                     "ORDER BY thoiGian DESC";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenBang);
            ps.setString(2, maDoiTuong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public List<NhatKyThaoTac> timTheoKhoangThoiGian(LocalDateTime tu, LocalDateTime den) {
        List<NhatKyThaoTac> ds = new ArrayList<>();
        String sql = "SELECT * FROM NhatKyThaoTac WHERE thoiGian BETWEEN ? AND ? " +
                     "ORDER BY thoiGian DESC";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(tu));
            ps.setTimestamp(2, Timestamp.valueOf(den));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public List<NhatKyThaoTac> layTatCa() {
        return timKiem("1=1", null);
    }
    
    // ===== HELPER METHODS =====
    
    private List<NhatKyThaoTac> timKiem(String dieuKien, String giatri) {
        List<NhatKyThaoTac> ds = new ArrayList<>();
        String sql = "SELECT * FROM NhatKyThaoTac WHERE " + dieuKien + 
                     " ORDER BY thoiGian DESC";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (giatri != null) ps.setString(1, giatri);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    private NhatKyThaoTac mapResultSet(ResultSet rs) throws SQLException {
        return new NhatKyThaoTac(
            rs.getInt("maLog"),
            rs.getString("maNV"),
            LoaiThaoTac.valueOf(rs.getString("loaiThaoTac")),
            rs.getString("tenBang"),
            rs.getString("maDoiTuong"),
            rs.getString("noiDungCu"),
            rs.getString("noiDungMoi"),
            rs.getTimestamp("thoiGian").toLocalDateTime(),
            rs.getString("ghiChu")
        );
    }
}