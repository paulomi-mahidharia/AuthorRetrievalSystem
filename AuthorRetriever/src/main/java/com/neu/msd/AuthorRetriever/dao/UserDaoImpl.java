package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.User;

public class UserDaoImpl implements UserDao {

	private Connection conn = DatabaseConnection.getConn();
	
	/**
	 * @param username
	 * @param password
	 * @param queryString
	 * @return loginId of user
	 * This method is used to validate user login
	 */

	public int login(String username, String password, String queryString) {
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			return 0;
		} else {
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(queryString);
				stmt.setString(1, username);

				ResultSet rs = stmt.executeQuery();
				if (rs.next())
					if (rs.getString("password").equals(password)) {
						return rs.getInt("UserID");
					}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		}
		return 0;
	}

	/**
	 * @param queryString
	 * @param user
	 * @return UserId of user
	 * This method is use to register User in system
	 */

	@Override
	public int registerUser(String queryString, User user) {
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(queryString);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 1;
	}
}