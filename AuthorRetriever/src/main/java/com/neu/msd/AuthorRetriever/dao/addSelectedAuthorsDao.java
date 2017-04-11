package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;

public interface addSelectedAuthorsDao {

	/**
	 * add authors selected by the user to a list 
	 * @param authorId, List of authors to be inserted
	 * @return Nothing
	 */
	public void addSelectedAuthors(int userId, List<Author> authors) throws SQLException;

	
}
