package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.AuthorPaper;

/**
 * This service declares the functions related to an author
 * @author paulomimahidharia
 *
 */
public interface AuthorInfoService {
	
	/**
	 * Retrieves an author's profile for the given authorId
	 * @param authorId Id of an author
	 * @return author's profile information 
	 */
	public Author getAuthorProfile(int authorId);
	
	public List<AuthorPaper> getAuthorPapers(int authorId) throws SQLException;
	
}
