package com.neu.msd.AuthorRetriever.service;

import java.sql.Connection;

import com.neu.msd.AuthorRetriever.dao.UserDao;
import com.neu.msd.AuthorRetriever.dao.UserDaoImpl;
import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;

public class UserServiceImpl implements UserService{

	UserDao userDao=new UserDaoImpl();
	
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		Connection conn = DatabaseConnection.getConn();
		String queryString="select password from UserCredentials where username=?";
		boolean loginSuccessful=userDao.login(username, password, queryString);
		return loginSuccessful;
	}

}
