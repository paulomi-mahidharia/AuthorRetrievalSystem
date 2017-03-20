package com.neu.msd.dblp;

import org.junit.Test;
import org.mockito.Mockito;
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
   
   @Test
   public void testNullUsernamePassword(){
      
      UserService userService = new UserServiceImpl();
      assertFalse(userService.login(null, null));
   }
   
   @Test
   public void testEmptyUsernameNullPassword(){
      
      UserService userService = new UserServiceImpl();
      assertFalse(userService.login("", null));
   }
   
   @Test
   public void testNullUsernameEmptyPassword(){
      
      UserService userService = new UserServiceImpl();
      assertFalse(userService.login(null, ""));
   }
   
   @Test
   public void testValidUsernamePassword(){
	  
      UserService userService = Mockito.mock(UserService.class);
      Mockito.when(userService.login("paulomi", "paulomi")).thenReturn(true);
      assertTrue(userService.login("paulomi", "paulomi"));
   }
   
   @Test
   public void testValidUsernameInvalidPassword(){
	  
      UserService userService = Mockito.mock(UserService.class);
      Mockito.when(userService.login("paulomi", "p")).thenReturn(false);
      assertFalse(userService.login("paulomi", "p"));
   }
   
}
