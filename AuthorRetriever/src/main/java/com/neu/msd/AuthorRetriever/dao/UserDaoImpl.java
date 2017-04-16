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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return 0;
	}

	@Override
	public List<Author> getAuthorsForUser(int userId, String queryString) {
		PreparedStatement stmt;
		List<Author> authorList = new ArrayList<Author>();
		try {
			System.out.println(userId);
			stmt = conn.prepareStatement(queryString);
			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Author author = new Author();
				author.setAuthorId(rs.getInt("Id"));
				author.setName(rs.getString("Name"));
				author.setAffiliation(rs.getString("Affiliation"));
				author.setUrl(rs.getString("url"));
				authorList.add(author);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(authorList.size());
		return authorList;
	}

	@Override
	public int registerUser(String queryString, User user) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(queryString);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement .executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1;
	}

	@Override
	public boolean deleteSelectedAuthor(int userId, int authorId,String queryString) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(queryString);
			preparedStatement.setInt(1,authorId);
			preparedStatement.setInt(2,userId);
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
}