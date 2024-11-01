package eletrosales.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoConnection {
	
	private String driver="com.mysql.cj.jdbc.Driver";
	private String url="jdbc:mysql://localhost:3306/eletrosales";
	private String user="root";
	private String password="";
	
	private Connection conectar() {
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
	public void teste() {
		try {
			Connection con=conectar();
			System.out.println(con);
			System.out.println("conexao feita com sucesso!");
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
