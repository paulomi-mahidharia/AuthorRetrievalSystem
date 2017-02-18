package com.neu.msd.dblp.Service;

import java.util.List;

import com.neu.msd.dblp.model.*;
import com.neu.msd.dblp.model.SearchCriteria;

public interface AuthorService {
	
	/**
	 * Searches for author by name
	 * @param name
	 * @return Author with matching name
	 */
	public Author getAuthor(String name);
	
	/**
	 * search authors based on search criteria
	 * @param criteria
	 * @return List of Authors matching the search criteria.
	 */
	public List<Author> searchAuthors(SearchCriteria criteria);
	
	
}
