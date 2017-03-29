package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;

public interface UserDao {
	
	public boolean login(String username, String password) throws SQLException;
}
