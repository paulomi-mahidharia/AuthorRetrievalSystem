package com.neu.msd.AuthorRetriever.dao;

import com.neu.msd.AuthorRetriever.model.User;;

public interface UserDao {

	/**
	 * @param username
	 * @param password
	 * @param queryString
	 * @return loginId of user
	 * This method is used to validate user login
	 */
	public int login(String username, String password, String queryString);
	
	/**
	 * @param queryString
	 * @param user
	 * @return UserId of user
	 * This method is use to register User in system
	 */
	public int registerUser(String queryString, User user);

}
