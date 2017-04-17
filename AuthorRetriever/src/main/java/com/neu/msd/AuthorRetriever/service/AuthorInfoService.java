package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.AuthorPaper;
import com.neu.msd.AuthorRetriever.model.Conference;

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
	
	/**
	 * Retrieves a list of published papers by author for the given authorId
	 * @param authorId Id of an author
	 * @return list of published papers
	 */
	
	public List<AuthorPaper> getAuthorPapers(int authorId) throws SQLException;
	
	/**
	 * Retrieves a list of conferences attended by author for the given authorId
	 * @param authorId Id of an author
	 * @return list of conference papers
	 */
	public List<Conference> getAuthorConferenceServed(int authorId) throws SQLException;
	
}
