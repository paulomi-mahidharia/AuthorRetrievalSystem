package com.neu.msd.AuthorRetriever.dao;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.User;;

public interface UserDao {

	public int login(String username, String password, String queryString);

	public List<Author> getAuthorsForUser(int userId, String queryString);
	
	public int registerUser(String queryString,User user);
	
	public boolean deleteSelectedAuthor(int userId, int authorId,String queryString);
}
