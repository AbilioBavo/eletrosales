package eletrosales.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoConnection {
	
	private static final String driver="com.mysql.cj.jdbc.Driver";
	private static final String  url="jdbc:mysql://localhost:3306/eletrosales";
	private static final String user="root";
	private static final String password="";
	
	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch( Exception e) {
			System.out.println(e);
			return null;
		}
	}
//	public void teste() {
//		try {
//			Connection con=getConnection();
//			System.out.println(con);
//			System.out.println("conexao feita com sucesso!");
//			con.close();
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//	}
}
