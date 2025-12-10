package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.BanAn;
import entity.LoaiBan;
import entity.NhanVien;

public class LoaiBanDAO {
	public List<LoaiBan> getAllLoaiBan() {
		List<LoaiBan> dsLoaiBan = new ArrayList<LoaiBan>();
		Connection con= ConnectDB.getConnection();
		String sql = "Select * From LoaiBanAn";
		try (PreparedStatement stmt= con.prepareStatement(sql)){

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maLoai = rs.getString("maLoaiBan");
				String tenLoai = rs.getString("tenLoaiBan");
				
				LoaiBan loaiban = new LoaiBan(maLoai, tenLoai);
				dsLoaiBan.add(loaiban);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoaiBan;
	}
	
	public String layMaTheoTen(String maLoaiBan) {
		String ma = null;
		Connection con = ConnectDB.getConnection();
		try {
			
			String sql = "SELECT tenLoaiBan FROM LoaiBanAn WHERE maLoaiBan = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maLoaiBan);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				ma = rs.getString("tenLoaiBan");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}
	
	public LoaiBan layLoaiBanTheoTen(String tenLoaiBan) {
	    LoaiBan loaiBan = null;
	    String sql = "SELECT * FROM LoaiBanAn WHERE tenLoaiBan = ?";

	    Connection con = ConnectDB.getConnection();
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, tenLoaiBan);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                String maLoaiBan = rs.getString("maLoaiBan");
	                String ten = rs.getString("tenLoaiBan");
	                loaiBan = new LoaiBan(maLoaiBan, ten);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("❌ Lỗi khi lấy loại bàn theo tên: " + tenLoaiBan);
	        e.printStackTrace();
	    }

	    return loaiBan;
	}
	
	public LoaiBan layLoaiBanTheoMa(String maLoaiBan) {
	    LoaiBan loaiBan = null;
	    String sql = "SELECT * FROM LoaiBanAn WHERE maLoaiBan = ?";

	    Connection con = ConnectDB.getConnection();
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, maLoaiBan);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                String maLoai= rs.getString("maLoaiBan");
	                String ten = rs.getString("tenLoaiBan");
	                loaiBan = new LoaiBan(maLoai, ten);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("❌ Lỗi khi lấy loại bàn theo tên: " + maLoaiBan);
	        e.printStackTrace();
	    }

	    return loaiBan;
	}
	
	public String taoMaLoaiBanMoi() {
		Connection con = ConnectDB.getConnection();
		String maMoi = "LB001";
		PreparedStatement stmt = null;
		try{
			String sql = "SELECT MAX(CAST(SUBSTRING(maLoaiBan, 3, LEN(maLoaiBan)-2) AS INT)) AS maxSo FROM LoaiBanAn";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int maxSo = rs.getInt("maxSo");
				int soMoi = maxSo + 1;
				maMoi = String.format("LB%03d", soMoi);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return maMoi;
	}
	
	public boolean themLoaiBan(LoaiBan loaiBan) {
	    String sql = """
	        INSERT INTO LoaiBanAn (maLoaiBan, tenLoaiBan)
	        VALUES (?, ?)
	        """;

	    Connection con = ConnectDB.getConnection();
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, loaiBan.getMaLoaiBan());
	        ps.setString(2, loaiBan.getTenLoaiBan());
	       
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("❌ Lỗi khi thêm loại bàn ăn mới:");
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean xoaLoaiBan(String maLoaiBan) {
	    String sql = "DELETE FROM LoaiBanAn WHERE maLoaiBan = ?";
	    Connection con = ConnectDB.getConnection();
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, maLoaiBan);
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("❌ Lỗi khi xóa loại bàn ăn có mã: " + maLoaiBan);
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	public boolean suaLoaiBan(LoaiBan loaiBan) {
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "UPDATE LoaiBanAn SET tenLoaiBan=? WHERE maLoaiBan=?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, loaiBan.getTenLoaiBan());
                stmt.setString(2, loaiBan.getMaLoaiBan());
                return stmt.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
