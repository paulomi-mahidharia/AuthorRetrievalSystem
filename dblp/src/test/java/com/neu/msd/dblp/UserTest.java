package com.neu.msd.dblp;

import org.junit.Test;

import com.neu.msd.dblp.service.UserService;
import com.neu.msd.dblp.service.UserServiceImpl;

import junit.framework.TestCase;

public class UserTest extends TestCase{
	
	// test method to check on empty username and password
   @Test
   public void testEmptyUsernamePassword(){
      
      UserService userService = new UserServiceImpl();
      assertFalse(userService.login("", ""));
   }
   
}
