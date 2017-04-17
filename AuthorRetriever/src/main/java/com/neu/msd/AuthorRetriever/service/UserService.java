package com.neu.msd.AuthorRetriever.service;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.User;

/**
 * @author rushi
 *
 */

public interface UserService {
	/**
	 * User credentials to login to the system
	 * 
	 * @param Name
	 *            of the user
	 * @param password
	 *            of the user
	 * @return If the user has valid credentials to access the system.
	 */
	public boolean login(String username, String password);
	
	/**
	 * 
	 * Add all selected authors to database
	 * @Given:A list of short listed authors
	 * @Return:Void
	 *
	 */
	
	public void addSelectedAuthors(List<Author> authors);
	
	/**
	 * Gets a list of short listed authors
	 * @Given:Void
	 * @return:Returns a list of author
	 *
	 */
	public List<Author> getAllAuthorsForUser();
	
	/**
	 * Register User to system.
	 * @Given:A user object that is used to store user credentials in DB
	 * @return:A boolean
	 *
	 */
	public boolean registerUser(User user);
	/**
	 * Deleted Selected Author from short listed author 
	 * @Given:A authorId which is integer
	 * @return:A boolean
	 *
	 */
	
	
	public boolean deleteSelectedAuthor(int authorId);
	

}
