package com.neu.msd.AuthorRetriever.service;

import com.neu.msd.AuthorRetriever.model.*;

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
