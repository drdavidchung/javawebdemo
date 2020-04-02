package com.lmonkey.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Basedao {
static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //com.mysql.cj.jdbc.Driver
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getconn() {
		Connection conn = null;
		try {
//		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmonkeyshop?useSSL=false&&serverTimezone=CST","root","2xjuxaru");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmonkeyshop?serverTimezone=GMT","root","2xjuxaru");
//		jdbc:mysql://localhost:3306/testjdbc?serverTimezone=GMT
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		
		return conn;
	}
	
	public static int executeIUD(String sql, Object[] params) {
		int count = 0;
		Connection conn = Basedao.getconn();
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			
			for(int i=0; i<params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Basedao.closeall(null, ps, conn);
		}
		
		return count;
		
	}
	
	public static void closeall(ResultSet rs, PreparedStatement ps, Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
			
			
		
	}

}
