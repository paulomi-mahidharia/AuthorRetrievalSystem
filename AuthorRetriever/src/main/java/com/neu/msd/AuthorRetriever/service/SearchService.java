package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.*;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;

/**
 * 
 * @author paulomimahidharia
 *
 */
public interface SearchService {
	
	/**
	 * Search for authors based on the given search criteria
	 * @param criteria search criteria 
	 * @return list of authors matching the criteria
	 * @throws SQLException 
	 */
	public List<Author> searchAuthorsByCriteria(SearchCriteria criteria) throws SQLException;
	
}
