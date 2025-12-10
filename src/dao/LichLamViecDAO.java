package dao;

import connectDB.ConnectDB;
import entity.LichLamViec;
import entity.NhanVien;
import entity.CaLamViec;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LichLamViecDAO {
	private NhanVienDAO nhanVienDAO = new NhanVienDAO();
	private CaLamViecDAO caLamViecDAO = new CaLamViecDAO();

	public boolean themLichLamViec(LichLamViec lichLam) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO LichLamViec (ngayLamViec, maNV, maCa, trangThai) VALUES (?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);

			stmt.setDate(1, java.sql.Date.valueOf(lichLam.getNgayLamViec()));
	        stmt.setString(2, lichLam.getNhanVien().getMaNV());
	        stmt.setString(3, lichLam.getCaLamViec().getMaCa());
	        stmt.setBoolean(4, lichLam.isTrangThai());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//
	public List<LichLamViec> layDanhSachLichLamViec() {
		List<LichLamViec> dsLich = new ArrayList<>();
		Connection con = ConnectDB.getConnection();
		try {
			if (con == null || con.isClosed()) {
				ConnectDB.getInstance().connect();
				con = ConnectDB.getConnection();
			}
			String sql = "SELECT  l.maCa, l.maNV, l.ngayLamViec, l.trangThai, c.tenCa, c.gioVaoLam, c.gioTanLam, c.trangThai, n.hoTen "
					+ "FROM LichLamViec l " + "JOIN CaLamViec c ON l.maCa = c.maCa "
					+ "JOIN NhanVien n ON l.maNV = n.maNV";
			try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					CaLamViec ca = new CaLamViec(rs.getString("maCa"), rs.getString("tenCa"),
							rs.getTime("gioVaoLam").toLocalTime(), rs.getTime("gioTanLam").toLocalTime(),
							rs.getBoolean("trangThai"));
					NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"));
					LichLamViec lich = new LichLamViec();
					dsLich.add(lich);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLich;
	}

	// Lấy tất cả lịch làm việc
	public List<LichLamViec> getAllLichLamViec() {
		List<LichLamViec> dsLich = new ArrayList<>();
		String sql = "SELECT * FROM LichLamViec";
		try (Connection con = ConnectDB.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				NhanVien nv = nhanVienDAO.getNhanVienTheoMa(rs.getString("maNV"));
				CaLamViec ca = caLamViecDAO.timCaLamViecTheoMa(rs.getString("maCa"));
				dsLich.add(
						new LichLamViec(rs.getDate("ngayLamViec").toLocalDate(), nv, ca, rs.getBoolean("trangThai")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLich;
	}

	// Lấy danh sách lịch làm việc theo ca và ngày
//	public List<LichLamViec> layLichLamViecTheoCaVaNgayLamViec(String maCa, LocalDate ngayLam) {
//		List<LichLamViec> dsLich = new ArrayList<>();
//		Connection con = ConnectDB.getConnection();
//		ConnectDB.getInstance().connect();
//		try {
//			String sql = """
//					    SELECT
//					        l.maLich, l.maCa, l.maNV, l.ngayLamViec, l.trangThai AS trangThaiLich,
//					        c.tenCa, c.gioVaoLam, c.gioTanLam, c.trangThai AS trangThaiCa,
//					        n.hoTen
//					    FROM LichLamViec l
//					    JOIN CaLamViec c ON l.maCa = c.maCa
//					    JOIN NhanVien n ON l.maNV = n.maNV
//					    WHERE l.maCa = ? AND l.ngayLamViec = ?
//					""";
//
//			try (PreparedStatement stmt = con.prepareStatement(sql)) {
//				stmt.setString(1, maCa);
//				stmt.setDate(2, Date.valueOf(ngayLam));
//
//				try (ResultSet rs = stmt.executeQuery()) {
//					while (rs.next()) {
//						CaLamViec ca = new CaLamViec(rs.getString("maCa"), rs.getString("tenCa"),
//								rs.getTime("gioVaoLam").toLocalTime(), rs.getTime("gioTanLam").toLocalTime(),
//								rs.getBoolean("trangThaiCa"));
//
//						NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"));
//
//						LichLamViec lich = new LichLamViec(rs.getDate("ngayLamViec").toLocalDate(), nv, ca,
//								rs.getBoolean("trangThaiLich"));
//
//						dsLich.add(lich);
//					}
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dsLich;
//	}

	public List<LichLamViec> layLichLamViecTheoCaVaNgay(String maCa, LocalDate ngayLam) {
		List<LichLamViec> dsLich = new ArrayList<>();

		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT l.maCa, l.maNV, l.ngayLamViec, l.trangThai as TrangThaiLich, c.tenCa, c.gioVaoLam, c.gioTanLam, c.trangThai as TrangThaiCa, n.hoTen "
					+ "FROM LichLamViec l " + "JOIN CaLamViec c ON l.maCa = c.maCa "
					+ "JOIN NhanVien n ON l.maNV = n.maNV " + "WHERE l.maCa = ? AND l.ngayLamViec = ?";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, maCa);
				stmt.setDate(2, Date.valueOf(ngayLam));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					CaLamViec ca = new CaLamViec(rs.getString("maCa"), rs.getString("tenCa"),
							rs.getTime("gioVaoLam").toLocalTime(), rs.getTime("gioTanLam").toLocalTime(),
							rs.getBoolean("TrangThaiCa"));
					NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"));
					LichLamViec lich = new LichLamViec();
					lich.setCaLamViec(ca);
					lich.setNhanVien(nv);
					lich.setNgayLamViec(rs.getDate("ngayLamViec").toLocalDate());
					lich.setTrangThai(rs.getBoolean("TrangThaiLich"));
					dsLich.add(lich);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLich;
	}

	public List<NhanVien> layNhanVienTrongCa(String maCa, LocalDate ngayLam) {
		List<NhanVien> dsNhanVien = new ArrayList<>();
		Connection con = ConnectDB.getConnection();
		try {
			if (con == null || con.isClosed()) {
				ConnectDB.getInstance().connect();
				con = ConnectDB.getConnection();
			}
			String sql = "SELECT n.maNV, n.hoTen FROM NhanVien n "
					+ "WHERE n.maNV NOT IN (SELECT maNV FROM LichLamViec WHERE maCa = ? AND ngayLamViec = ?)";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, maCa);
				stmt.setDate(2, Date.valueOf(ngayLam));
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"));
						dsNhanVien.add(nv);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}

	public boolean kiemTraCaDaPhanTheoNgay(String maCa, LocalDate ngay) {
		Connection con = ConnectDB.getConnection();
		if (con == null) {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
		}

		String sql = "SELECT COUNT(*) FROM LichLamViec WHERE maCa = ? AND ngayLamViec = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maCa);
			stmt.setDate(2, java.sql.Date.valueOf(ngay));

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0; // true nếu đã có nhân viên được phân ca này ngày đó
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // mặc định chưa phân
	}
//
	public List<NhanVien> layNhanVienTheoNgay(LocalDate ngay) {
		List<NhanVien> result = new ArrayList<>();
		Connection con = ConnectDB.getConnection();

		String sql = "SELECT DISTINCT maNV FROM LichLamViec WHERE ngayLamViec = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setDate(1, java.sql.Date.valueOf(ngay));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString("maNV");
				NhanVien nv = nhanVienDAO.getNhanVienTheoMa(maNV);
				if (nv != null) {
					result.add(nv);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	 //Cập nhật trạng thái (Làm / Nghỉ) cho một nhân viên trong ca vào ngày cụ thể
	public boolean capNhatTrangThai(LichLamViec lich) {
	    String sql = "UPDATE LichLamViec SET trangThai = ? WHERE maNV = ? AND maCa = ? AND ngayLamViec = ?";
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setBoolean(1, lich.isTrangThai()); // trạng thái mới
	        ps.setString(2, lich.getNhanVien().getMaNV()); // mã nhân viên
	        ps.setString(3, lich.getCaLamViec().getMaCa()); // mã ca
	        ps.setDate(4, java.sql.Date.valueOf(lich.getNgayLamViec())); // ngày làm việc

	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
