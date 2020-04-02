package com.lmonkey.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lmonkey.dao.Basedao;
import com.lmonkey.entity.LMONKEY_USER;

public class LMONKEY_USERDao {
	public static int insert(LMONKEY_USER u) {
			String sql = "insert into LMONKEY_USER values(?,?,?,?, DATE_FORMAT(?, '%Y-%m-%d'),?,?,?,?,?)";
			Object[] params = {
						u.getUSER_ID(),
						u.getUSER_NAME(),
						u.getUSER_PASSWORD(),
						u.getUSER_SEX(),
						u.getUSER_BIRTHDAY(),
						u.getUSER_IDENITY_CODE(),
						u.getUSER_EMAIL(),
						u.getUSER_MOBILE(),
						u.getUSER_ADDRESS(),
						u.getUSER_STATUS()};
			
			return Basedao.executeIUD(sql, params);
	}
	
	public static ArrayList<LMONKEY_USER> selectAll() {
		ArrayList<LMONKEY_USER> list = new ArrayList<LMONKEY_USER>();
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		try {
			String sql = "select * from LMONKEY_USER order by USER_BIRTHDAY";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				LMONKEY_USER u = new LMONKEY_USER(
						rs.getString("USER_ID"),
						rs.getString("USER_NAME"), 
						rs.getString("USER_PASSWORD"), 
						rs.getString("USER_SEX"), 
						rs.getString("USER_BIRTHDAY"), 
						rs.getString("USER_IDENITY_CODE"), 
						rs.getString("USER_EMAIL"), 
						rs.getString("USER_MOBILE"), 
						rs.getString("USER_ADDRESS"), 
						rs.getInt("USER_STATUS")						
					);
				list.add(u);	//
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
}
