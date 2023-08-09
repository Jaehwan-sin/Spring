package com.tech.db;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBcon {
	// DB 접속
	static Connection con;
	
	public static Connection getConnection() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/springxe");
			con = dataSource.getConnection();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}
}
