package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
import com.neu.msd.AuthorRetriever.model.Author;

public class SearchSimilarAuthorsDaoImpl implements SearchSimilarAuthorsDao {

	private Connection conn = DatabaseConnection.getConn();
	
	/**
	 * @param A query String to retrieve a list of similar author from database
	 * @return A list of Author from database
	 * Retrieve a list of  similar author  from database.
	 * @throws SQL Exception
	 */

	@Override
	public List<Author> searchSimilarAuthors(String queryString) throws SQLException {
		System.out.println(queryString);
		PreparedStatement stmt = conn.prepareStatement(queryString);
		ResultSet rs = stmt.executeQuery();
		List<Author> authors = new ArrayList<Author>();

		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(Integer.parseInt(rs.getString(1)));
			a.setName(rs.getString(2));
			a.setAffiliation(rs.getString("Affiliation"));
			a.setUrl(rs.getString("url"));
			authors.add(a);
		}
		return authors;
	}
}
