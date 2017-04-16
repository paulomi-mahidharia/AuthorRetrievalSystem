package com.neu.msd.AuthorRetriever.dao;

import com.neu.msd.AuthorRetriever.model.User;;

public interface UserDao {

	/**
	 * @param username
	 * @param password
	 * @param queryString
	 * @return
	 */
	public int login(String username, String password, String queryString);
	
	/**
	 * @param queryString
	 * @param user
	 * @return
	 */
	public int registerUser(String queryString, User user);

}
