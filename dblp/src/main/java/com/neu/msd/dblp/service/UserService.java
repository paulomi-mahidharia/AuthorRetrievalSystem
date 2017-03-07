package com.neu.msd.dblp.service;

public interface UserService {
	/**
	 * User credentials to login to the system
	 * @param Name of the user
	 * @param password of the user
	 * @return If the user has valid credentials to access the system.
	 */
	public boolean login(String username, String password);
}
