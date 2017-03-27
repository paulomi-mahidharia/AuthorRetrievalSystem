package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;

public interface SearchSimilarProfileService {

	/**
	 * Search for authors who have a matching profile as the given author
	 * @param author author whose matching profile is to be found
	 * @return list of authors with matching profile
	 * @throws SQLException 
	 */
	public List<Author> searchSimilarAuthorProfiles(Author author) throws SQLException;
	
}
