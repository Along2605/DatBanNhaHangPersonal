package dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.CaLamViec;

public class CaLamViecDAO {

	public boolean themCaLamViec(CaLamViec ca) {
		Connection con = ConnectDB.getConnection();
		ConnectDB.getInstance().connect();
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO CaLamViec(maCa, tenCa, gioVaoLam, gioTanLam, trangThai) VALUES (?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ca.getMaCa());
			stmt.setString(2, ca.getTenCa());
			stmt.setTime(3, Time.valueOf(ca.getGioVaoLam()));
			stmt.setTime(4, Time.valueOf(ca.getGioTanLam()));
			stmt.setBoolean(5, ca.isTrangThai());
			return stmt.executeUpdate() > 0;

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean xoaCaLam(String ma) {
		Connection con = ConnectDB.getConnection();
		ConnectDB.getInstance().connect();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "Delete From CaLamViec Where maCa = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capNhatCaLamViec(CaLamViec ca) {
		Connection con = ConnectDB.getConnection();
		ConnectDB.getInstance().connect();
		PreparedStatement stmt;
		try {
			String sql = "UPDATE CaLamViec SET gioVaoLam = ?, gioTanLam=?, trangThai=?, tenCa = ? WHERE maCa=?";
			stmt = con.prepareStatement(sql);
			stmt.setTime(1, Time.valueOf(ca.getGioVaoLam()));
			stmt.setTime(2, Time.valueOf(ca.getGioTanLam()));
			stmt.setBoolean(3, ca.isTrangThai());
			stmt.setString(4, ca.getTenCa());
			stmt.setString(5, ca.getMaCa());

			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<CaLamViec> layDanhSachCaLamViec() {
		List<CaLamViec> ds = new ArrayList<>();
		Connection con = ConnectDB.getConnection();
		ConnectDB.getInstance().connect();
		Statement stmt = null;
		try {
			String sql = "SELECT * FROM CaLamViec";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CaLamViec ca = new CaLamViec(rs.getString("maCa"), rs.getString("tenCa"),
						rs.getTime("gioVaoLam").toLocalTime(), // chuyển Time -> LocalTime
						rs.getTime("gioTanLam").toLocalTime(), rs.getBoolean("trangThai"));
				ds.add(ca);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return ds;
	}

	public CaLamViec timCaLamViecTheoMa(String maCa) {
		Connection con = ConnectDB.getConnection();

		String sql = "SELECT * FROM CaLamViec WHERE maCa=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maCa);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new CaLamViec(rs.getString("maCa"), rs.getString("tenCa"), rs.getTime("gioVaoLam").toLocalTime(),
						rs.getTime("gioTanLam").toLocalTime(), rs.getBoolean("trangThai"));
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String generateMaCaLam() {
		Connection con = ConnectDB.getConnection();
		
		String maMoi = "CA001";
		String sql = "SELECT MAX(CAST(SUBSTRING(maCa, 3, LEN(maCa)-2) AS INT)) AS maxSo FROM CaLamViec";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int maxSo = rs.getInt("maxSo");
				int soMoi = maxSo + 1;
				maMoi = String.format("CA%03d", soMoi);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return maMoi;
	}

	public List<CaLamViec> timCaLamViecTheoTen(String tenCa) {
		Connection con = ConnectDB.getConnection();
		ConnectDB.getInstance().connect();
		PreparedStatement stmt = null;
		ArrayList<CaLamViec> dsCaLam = new ArrayList<CaLamViec>();
		try {
			String sql = "SELECT * FROM CaLamViec WHERE tenCa like ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + tenCa + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCa = rs.getString("maCa");
				String ten = rs.getString("tenCa");
				LocalTime gioVao = rs.getObject("gioVaoLam", LocalTime.class);
				LocalTime gioTan = rs.getObject("gioTanLam", LocalTime.class);
				Boolean status = rs.getBoolean("trangThai");
				
				CaLamViec ca = new CaLamViec(maCa, ten, gioVao, gioTan, status);
				dsCaLam.add(ca);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return dsCaLam;
	}
	
	
}