package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;



/**
 * Below method is the abstract implementation of the method search similar authors.
 *  
 *
 */
public interface SearchSimilarAuthorsDao {
	/**
	 * @param A query String to retrieve a list of similar author from database
	 * @return A list of Author from database
	 * Retrieve a list of  similar author  from database.This is a abstract implementation of the methods
	 */

	public List<Author> searchSimilarAuthors(String queryString) throws SQLException;

}
