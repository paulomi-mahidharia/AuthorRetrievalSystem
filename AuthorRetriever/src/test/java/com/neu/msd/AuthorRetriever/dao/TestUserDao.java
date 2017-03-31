package com.neu.msd.AuthorRetriever.dao;

import org.junit.Test;

import junit.framework.TestCase;

public class TestUserDao extends TestCase {

	@Test
	public void testValidUser(){
		UserDao userDao = new UserDaoImpl();
		String username= "a";
		String password = "b";
		
		assertTrue(userDao.login(username, password));
	}
	
	@Test
	public void testInvalidUser(){
		UserDao userDao = new UserDaoImpl();
		String username= "a";
		String password = "";
		
		assertFalse(userDao.login(username, password));
	}
	
	// more cases
}
