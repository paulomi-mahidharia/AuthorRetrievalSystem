package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
import com.neu.msd.AuthorRetriever.model.Author;


public class SelectedAuthorsDaoImpl implements SelectedAuthorsDao {
	
	private Connection conn;
	PreparedStatement add_selected_authors;
	PreparedStatement retrieveSelectedAuthors;
	PreparedStatement removeSelectedAuthor;
	
	
	public SelectedAuthorsDaoImpl() throws SQLException {
		conn = DatabaseConnection.getConn();
		conn.setAutoCommit(false);
		add_selected_authors = conn
				.prepareStatement("insert into selected_authors(user_Id, author_Id) " + "values (?,?)");
		retrieveSelectedAuthors = conn
				.prepareStatement("select author.* from author where id in (select Author_Id from selected_authors where user_Id = ?)");
		removeSelectedAuthor = conn
				.prepareStatement("Delete from selected_authors where author_Id= ? and user_id = ?");
	}

	/**
	 * add authors selected by the user to a list 
	 * @param authorId, List of authors to be inserted
	 * @return Nothing
	 * Add author to list of short listed author
	 */
	
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
	
	/**
	 * @param userId
	 * @return A list of author
	 * @throws SQLException
	 * Returns a list of short listed authors
	 */
	@Override
	public List<Author> getSelectedAuthorsForUser(int userId) throws SQLException {
		List<Author> authorList = new ArrayList<Author>();
		try {
			System.out.println(userId);
			retrieveSelectedAuthors.setInt(1, userId);

			ResultSet rs = retrieveSelectedAuthors.executeQuery();
			
			while (rs.next()) {
				Author author = new Author();
				author.setAuthorId(rs.getInt("Id"));
				author.setName(rs.getString("Name"));
				author.setAffiliation(rs.getString("Affiliation"));
				author.setUrl(rs.getString("url"));
				authorList.add(author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(authorList.size());
		return authorList;
	}
	
	/**
	 * @param userId
	 * @param authorId
	 * @return Boolean specifying whether author was deleted or not 
	 * @throws SQLException
	 * Delete a selected author
	 */
	
	
	@Override
	public boolean deleteSelectedAuthors(int userId, int authorId) throws SQLException {

		try {
			
			removeSelectedAuthor.setInt(1,authorId);
			removeSelectedAuthor.setInt(2,userId);
			System.out.println(removeSelectedAuthor.toString());
			removeSelectedAuthor.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Unable to remove selected record from shortlisted authors");
			e.printStackTrace();
			return false;
		}
		conn.commit();
		return true;
	}

}
