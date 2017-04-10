package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
public class UserDaoImpl implements UserDao{
	
	private Connection conn = DatabaseConnection.getConn();

	public boolean login(String username, String password,String queryString){
		
		if(username == null || username.isEmpty() || password == null || password.isEmpty()){
				return false;
		}else{
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(queryString);
				stmt.setString(1, username);
				
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				if(rs.getString("password").equals(password)){
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}

}
