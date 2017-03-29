package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.DatabaseConnection;

public class SearchAuthorDaoImpl implements SearchAuthorDao{

	private Connection conn = DatabaseConnection.getConn();
	
	@Override
	public List<Author> searchAuthorsByCriteria(String queryString) throws SQLException {
		
		PreparedStatement stmt = conn.prepareStatement(queryString);
		ResultSet rs = stmt.executeQuery();
		List<Author> authors = new ArrayList<Author>();
		
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(Integer.parseInt(rs.getString(1)));
			a.setName(rs.getString(2));
			authors.add(a);
		}
		return authors;
	}

	@Override
	public List<Author> searchSimilarAuthorProfiles(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

}
