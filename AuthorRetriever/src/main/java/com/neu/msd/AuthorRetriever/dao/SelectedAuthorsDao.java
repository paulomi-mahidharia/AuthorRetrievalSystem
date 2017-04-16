package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;

public interface SelectedAuthorsDao {

	/**
	 * add authors selected by the user to a list 
	 * @param authorId, List of authors to be inserted
	 * @return Nothing
	 */
	public void addSelectedAuthors(int userId, List<Author> authors) throws SQLException;

	/**
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	List<Author> getSelectedAuthorsForUser(int userId) throws SQLException;

	/**
	 * @param userId
	 * @param authorId
	 * @return
	 * @throws SQLException
	 */
	boolean deleteSelectedAuthors(int userId, int authorId) throws SQLException;

	
}
