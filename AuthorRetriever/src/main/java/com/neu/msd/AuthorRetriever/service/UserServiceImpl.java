package com.neu.msd.AuthorRetriever.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.dao.UserDao;
import com.neu.msd.AuthorRetriever.dao.UserDaoImpl;
import com.neu.msd.AuthorRetriever.dao.addSelectedAuthorsDao;
import com.neu.msd.AuthorRetriever.dao.addSelectedAuthorsDaoImpl;
import com.neu.msd.AuthorRetriever.model.Author;

public class UserServiceImpl implements UserService {

	static int loggedInUser;
	
	UserDao userDao = new UserDaoImpl();
	
	public static int getLoggedInId(){
		return loggedInUser;
	}
	
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		String queryString = "select UserId, Password from UserCredentials where username=?";
		loggedInUser = userDao.login(username, password, queryString);
		return (loggedInUser != 0);
	}

	public void addSelectedAuthors(List<Author> authors) {
		// TODO Auto-generated method stub
		try {
			addSelectedAuthorsDao selectedAuthorsDao = new addSelectedAuthorsDaoImpl();
			selectedAuthorsDao.addSelectedAuthors(loggedInUser, authors);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
