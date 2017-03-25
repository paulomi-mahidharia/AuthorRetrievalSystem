package com.neu.msd.AuthorRetriever.service;

import com.neu.msd.AuthorRetriever.dao.UserDao;
import com.neu.msd.AuthorRetriever.dao.UserDaoImpl;

public class UserServiceImpl implements UserService{

	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImpl();
		Boolean loginSuccessful = false;
		try{
			loginSuccessful =  userDao.login(username, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return loginSuccessful;
	}

}
