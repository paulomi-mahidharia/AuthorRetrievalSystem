package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
import com.neu.msd.AuthorRetriever.model.Author;


public class AddSelectedAuthorsDaoImpl implements AddSelectedAuthorsDao {
	
	private Connection conn;
	PreparedStatement add_selected_authors;
	
	public AddSelectedAuthorsDaoImpl() throws SQLException {
		conn = DatabaseConnection.getConn();
		conn.setAutoCommit(false);
		add_selected_authors = conn
				.prepareStatement("insert into selected_authors(user_Id, author_Id) " + "values (?,?)");
	}

	@Override
	public void addSelectedAuthors(int userId, List<Author> authors) throws SQLException {

		for (Author author : authors) {
			add_selected_authors.setInt(1, userId);
			add_selected_authors.setInt(2, author.getAuthorId());
			add_selected_authors.addBatch();
			add_selected_authors.executeBatch();
		}

		conn.commit();
	}

}
