package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;

public interface SearchAuthorDao {
	/**
	 * Search for authors based on the given search criteria
	 * @param queryString
	 * @return list of authors matching the criteria
	 */
	public List<Author> searchAuthorsByCriteria(String queryString) throws SQLException;

	
}
