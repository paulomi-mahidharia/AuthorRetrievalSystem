package com.neu.msd.AuthorRetriever.dao;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;;

public interface UserDao {

	public int login(String username, String password, String queryString);

	public List<Author> getAuthorsForUser(int userId, String queryString);
}
