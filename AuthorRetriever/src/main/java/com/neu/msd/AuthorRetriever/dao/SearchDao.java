package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;

public interface SearchDao {
	/**
	 * Search for authors based on the given search criteria
	 * @param queryString
	 * @return list of authors matching the criteria
	 */
	public List<Author> searchAuthorsByCriteria(String queryString) throws SQLException ;
	
	/**
	 * Search for authors who have a matching profile as the given author
	 * @param author author whose matching profile is to be found
	 * @return list of authors with matching profile
	 */
	public List<Author> searchSimilarAuthorProfiles(Author author);

	
}
