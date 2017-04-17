package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
import com.neu.msd.AuthorRetriever.model.Author;

/**
 * @param A query String
 * @return A list of author by user criteria 
 * The below method returns a list of author based on criteria from database
 *
 */
public class SearchAuthorDaoImpl implements SearchAuthorDao{

	private Connection conn = DatabaseConnection.getConn();
	
	@Override
	public List<Author> searchAuthorsByCriteria(String queryString) throws SQLException {
		System.out.println(queryString);
		PreparedStatement stmt = conn.prepareStatement(queryString);
		ResultSet rs = stmt.executeQuery();
		List<Author> authors = new ArrayList<Author>();
		
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(Integer.parseInt(rs.getString(1)));
			a.setName(rs.getString(2));
			a.setAffiliation(rs.getString(3));
			a.setUrl(rs.getString(4));
			authors.add(a);
		}
		return authors;
	}
}
