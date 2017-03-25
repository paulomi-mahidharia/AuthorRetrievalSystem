package com.neu.msd.AuthorRetriever.service;

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
	 */
	public List<Author> searchAuthorsByCriteria(SearchCriteria criteria);
	
	/**
	 * Search for authors who have a matching profile as the given author
	 * @param author author whose matching profile is to be found
	 * @return list of authors with matching profile
	 */
	public List<Author> searchSimilarAuthorProfiles(Author author);
}
