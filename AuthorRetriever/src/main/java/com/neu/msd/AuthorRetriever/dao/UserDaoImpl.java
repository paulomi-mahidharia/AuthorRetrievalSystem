package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neu.msd.AuthorRetriever.service.DatabaseConnection;

public class UserDaoImpl implements UserDao{

	private Connection conn = DatabaseConnection.getConn();
	
	public boolean login(String username, String password) throws SQLException {
		//System.out.println(username);
		// TODO Auto-generated method stub
				if(username == null || username.isEmpty() || password == null || password.isEmpty()){
			return false;
		}else{
			PreparedStatement stmt = conn.prepareStatement("select password from UserCredentials where username=?");
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			if(rs.getString("password").equals(password)){
				return true;
			}
		return false;
		}
	}

}
