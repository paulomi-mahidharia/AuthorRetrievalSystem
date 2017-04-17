package com.neu.msd.AuthorRetriever.dao;

import org.junit.Test;

import junit.framework.TestCase;

public class TestUserDao extends TestCase {

	@Test
	public void testValidUser(){
		UserDao userDao = new UserDaoImpl();
		String username= "test";
		String password = "test";
		String queryString="select userId, password from UserCredentials where username=?";
		assertTrue(userDao.login(username, password,queryString) > 0);
	}
	
	@Test
	public void testInvalidUser(){
		UserDao userDao = new UserDaoImpl();
		String username= "xyz";
		String password = "dkdxXdcxcs";
		String queryString="select UserId,password from UserCredentials where username=?";
		assertFalse(userDao.login(username, password,queryString) > 0);
	}
	@Test
	public void testEmptyUsername(){
		UserDao userDao = new UserDaoImpl();
		String username= "";
		String password = "dkdxXdcxcs";
		String queryString="select UserId,password from UserCredentials where username=?";
		assertTrue(userDao.login(username, password,queryString) == 0);
	}
	@Test
	public void testEmptyPassword(){
		UserDao userDao = new UserDaoImpl();
		String username= "Abhinav";
		String password = "";
		String queryString="select UserId,password from UserCredentials where username=?";
		assertTrue(userDao.login(username, password,queryString) == 0);
	}

	@Test
	public void testEmptyUsernameEmptyPassword(){
		UserDao userDao = new UserDaoImpl();
		String username= "";
		String password = "";
		String queryString="select UserId,password from UserCredentials where username=?";
		assertTrue(userDao.login(username, password,queryString) == 0);
	}
	@Test
	public void testNullUsername(){
		UserDao userDao = new UserDaoImpl();
		String username= null;
		String password = "dkdxXdcxcs";
		String queryString="select UserId,password from UserCredentials where username=?";
		assertTrue(userDao.login(username, password,queryString) == 0);
	}
	public void testNullPassword(){
		UserDao userDao = new UserDaoImpl();
		String username= "Abhinav";
		String password = null;
		String queryString="select UserId,password from UserCredentials where username=?";
		assert(userDao.login(username, password,queryString) == 0);
	}
	public void testNullUsernameNullPassword(){
		UserDao userDao = new UserDaoImpl();
		String username= null;
		String password = null;
		String queryString="select UserId,password from UserCredentials where username=?";
		assertEquals(userDao.login(username, password,queryString),0);
	}
	// more cases
}
