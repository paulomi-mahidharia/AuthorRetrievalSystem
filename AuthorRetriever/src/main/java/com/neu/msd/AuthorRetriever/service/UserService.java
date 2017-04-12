package com.neu.msd.AuthorRetriever.service;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;

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

	public void addSelectedAuthors(List<Author> authors);

}
