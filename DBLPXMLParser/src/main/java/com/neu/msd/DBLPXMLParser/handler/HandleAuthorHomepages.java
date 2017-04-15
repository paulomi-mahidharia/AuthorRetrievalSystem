package com.neu.msd.DBLPXMLParser.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.DBLPXMLParser.config.DBConnection;
import com.neu.msd.DBLPXMLParser.model.AuthorHomepage;

public class HandleAuthorHomepages {

	private Connection conn;
	PreparedStatement stmt_author_homepage;

	public HandleAuthorHomepages() throws SQLException {
		conn = DBConnection.getConn();
		conn.setAutoCommit(false);
		stmt_author_homepage = conn
				.prepareStatement("insert into author_homepage(name, url) " + "values (?,?)");
	}

	public boolean insertRecords(List<AuthorHomepage> authorHomepage) throws SQLException {

		for (AuthorHomepage homepage : authorHomepage) {
			stmt_author_homepage.setString(1, homepage.getName());
			stmt_author_homepage.setString(2, homepage.getHomepage());
			stmt_author_homepage.addBatch();
			stmt_author_homepage.executeBatch();
		}
		conn.commit();
		return true;
	}

}
