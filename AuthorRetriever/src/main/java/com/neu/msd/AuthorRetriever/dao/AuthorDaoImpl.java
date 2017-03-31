package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
import com.neu.msd.AuthorRetriever.model.Author;

public class AuthorDaoImpl implements AuthorDao{
	
	private Connection conn = DatabaseConnection.getConn();

	@Override
	public List<Author> getAuthorByAuthorId(int authorId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
