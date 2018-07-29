package com.aqualevel.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.mysql.jdbc.Statement;

public class DaoUtil {
	
	private Connection con = null;
	
	@Autowired
	Environment env;
	
	public Connection startConnection() {
		
		String user = env.getProperty("spring.datasource.username");
        String pws = env.getProperty("spring.datasource.password");
        String driver = "com.mysql.jdbc.Driver";
        String url = env.getProperty("spring.datasource.url");
        
        try {
		
        	if (this.con == null) {
				Class.forName(driver);
				this.con = DriverManager.getConnection(url, user, pws);
			}
        	
		} catch (ClassNotFoundException cnf) {
			System.out.println("Erro de Drive: " + cnf.getMessage());
		}catch(SQLException sql) {
			System.out.println("Erro SQL" + sql.getMessage());
			System.out.println(sql.getSQLState());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
        
        return con;        
	}
	
	public void destroiConnection() {
		try {
			if (this.con != null) {
				con.close();
				this.con = null;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao fechar a conexão -->>" + e.getMessage());
		}	
	}
	
	public Statement getStatement() throws SQLException {
		return (Statement) this.startConnection().createStatement();
	}
	
	public PreparedStatement getPreparedStatement(String sql) throws SQLException  {
		return this.startConnection().prepareStatement(sql);
	}

}
