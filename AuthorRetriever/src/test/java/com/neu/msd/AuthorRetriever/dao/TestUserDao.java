package com.neu.msd.AuthorRetriever.dao;

import org.junit.Test;

import junit.framework.TestCase;

public class TestUserDao extends TestCase {

	@Test
	public void testValidUser(){
		UserDao userDao = new UserDaoImpl();
		String username= "a";
		String password = "b";
		String queryString="select password from UserCredentials where username=?";
		assertTrue(userDao.login(username, password,queryString));
	}
	
	@Test
	public void testInvalidUser(){
		UserDao userDao = new UserDaoImpl();
		String username= "a";
		String password = "";
		String queryString="select password from UserCredentials where username=?";
		assertFalse(userDao.login(username, password,queryString));
	}
	
	// more cases
}
