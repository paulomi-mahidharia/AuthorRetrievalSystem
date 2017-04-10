package com.neu.msd.AuthorRetriever.dao;

public interface UserDao {
	
	public boolean login(String username, String password,String queryString);
}
