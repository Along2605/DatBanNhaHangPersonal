package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhuVuc;
import entity.LoaiBan;

public class KhuVucDAO {
	public KhuVuc findById(String maKhuVuc) {
		String sql= "select * from KhuVuc where maKhuVuc=?";
		Connection con= ConnectDB.getConnection();
		try (PreparedStatement stmt= con.prepareStatement(sql)){
			stmt.setString(1, maKhuVuc);
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {
				return new KhuVuc(
						rs.getString("maKhuVuc"),
						rs.getString("tenKhuVuc"),
						rs.getString("viTri")
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<KhuVuc> getAll(){
		List<KhuVuc> dsKhuVuc= new ArrayList<KhuVuc>();
		Connection con= ConnectDB.getConnection();
		try (PreparedStatement stmt= con.prepareStatement("select * from KhuVuc");
				ResultSet rs= stmt.executeQuery()){
			while(rs.next()) {
				dsKhuVuc.add(new KhuVuc(
					rs.getString("maKhuVuc"),
					rs.getString("tenKhuVuc"),
					rs.getString("viTri")		
				));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhuVuc;
	}
	
	public String layMaTheoTen(String tenKhuVuc) {
	    String ma = null;
	    Connection con = ConnectDB.getConnection();
	    try {
	        
	        String sql = "SELECT maKhuVuc FROM KhuVuc WHERE tenKhuVuc = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, tenKhuVuc);
	        ResultSet rs = stmt.executeQuery();
	        if(rs.next()) {
	            ma = rs.getString("maKhuVuc");
	            
	        }
	        stmt.close();
	  } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return ma;
	}
	
	public ArrayList<String> layTenKhuVuc() {
		ArrayList<String> dsKhuVuc = new ArrayList<String>();
		Connection con = ConnectDB.getConnection();
		ConnectDB.getInstance().connect();
		PreparedStatement stmt = null;
		try {
			String sql = "SELECT tenKhuVuc From KhuVuc";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dsKhuVuc.add(rs.getString("tenKhuVuc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhuVuc;
	}
	
	public ArrayList<String> layViTri() {
		ArrayList<String> dsViTri = new ArrayList<String>();
		Connection con = ConnectDB.getConnection();
		ConnectDB.getInstance().connect();
		PreparedStatement stmt = null;
		try {
			String sql = "SELECT viTri From KhuVuc";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dsViTri.add(rs.getString("viTri"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsViTri;
	}
	
	
	public String taoMaKhuVucMoi() {
		Connection con = ConnectDB.getConnection();
		String maMoi = "KV001";
		PreparedStatement stmt = null;
		try{
			String sql = "SELECT MAX(CAST(SUBSTRING(maKhuVuc, 3, LEN(maKhuVuc)-2) AS INT)) AS maxSo FROM KhuVuc";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int maxSo = rs.getInt("maxSo");
				int soMoi = maxSo + 1;
				maMoi = String.format("KV%03d", soMoi);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return maMoi;
	}
	
	public boolean themKhuVuc(KhuVuc khuVuc) {
	    String sql = """
	        INSERT INTO KhuVuc (maKhuVuc, tenKhuVuc, viTri)
	        VALUES (?, ?)
	        """;

	    Connection con = ConnectDB.getConnection();
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, khuVuc.getMaKhuVuc());
	        ps.setString(2, khuVuc.getTenKhuVuc());
	        ps.setString(3, khuVuc.getViTri());
	       
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("❌ Lỗi khi thêm khu vực mới:");
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean xoaKhuVuc(String maKhuVuc) {
	    String sql = "DELETE FROM KhuVuc WHERE maKhuVuc = ?";
	    Connection con = ConnectDB.getConnection();
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, maKhuVuc);
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("❌ Lỗi khi xóa khu vực có mã: " + maKhuVuc);
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	public boolean suaKhuVuc(KhuVuc kv) {
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "UPDATE KhuVuc SET tenKhuVuc=?, viTri= ? WHERE maKhuVuc=?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, kv.getMaKhuVuc());
                stmt.setString(2, kv.getTenKhuVuc());
                stmt.setString(3, kv.getViTri());
                return stmt.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	public List<KhuVuc> loc(String khuVuc, String viTri) {
		List<KhuVuc> dsKV = new ArrayList<KhuVuc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement sta = null;
		String sql = "SELECT * FROM KhuVuc WHERE 1=1";
		if (viTri != null && !viTri.isEmpty()) {
			sql += "AND viTri LIKE ?";
		}
		if (khuVuc != null && !khuVuc.isEmpty()) {
			sql += "AND tenKhuVuc LIKE ?";
		}
		try {
			sta = con.prepareStatement(sql);
			int index = 1;
			if (viTri != null && !viTri.isEmpty()) {
				sta.setString(index++, "%" + viTri + "%");
			}
			if (khuVuc != null && !khuVuc.isEmpty()) {
				sta.setString(index++, "%" + khuVuc + "%");
			}
			
			ResultSet rs = sta.executeQuery();
			while(rs.next()) {
				String ma = rs.getString("maKhuVuc");
				String ten = rs.getString("tenKhuVuc");
				String gps = rs.getString("viTri");
				
				KhuVuc kv = new KhuVuc(ma, ten, gps);
				dsKV.add(kv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKV;
	}
	
	
}
