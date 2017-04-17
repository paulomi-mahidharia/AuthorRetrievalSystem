package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;

public interface SelectedAuthorsDao {

	/**
	 * add authors selected by the user to a list 
	 * @param authorId, List of authors to be inserted
	 * @return Nothing
	 * Add author to list of short listed author
	 */
	public void addSelectedAuthors(int userId, List<Author> authors) throws SQLException;

	/**
	 * @param userId
	 * @return A list of author
	 * @throws SQLException
	 * Returns a list of short listed authors
	 */
	List<Author> getSelectedAuthorsForUser(int userId) throws SQLException;

	/**
	 * @param userId
	 * @param authorId
	 * @return Boolean specifying whether author was deleted or not 
	 * @throws SQLException
	 * Delete a selected author
	 */
	boolean deleteSelectedAuthors(int userId, int authorId) throws SQLException;

	
}
