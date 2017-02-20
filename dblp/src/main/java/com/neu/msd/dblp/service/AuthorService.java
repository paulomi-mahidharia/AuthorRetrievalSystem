package com.neu.msd.dblp.service;

import com.neu.msd.dblp.model.*;

/**
 * This service declares the functions related to an author
 * @author paulomimahidharia
 *
 */
public interface AuthorService {
	
	/**
	 * Retrieves an author's profile for the given authorId
	 * @param authorId Id of an author
	 * @return author's profile information 
	 */
	public Author getAuthorProfile(int authorId);
	
}
