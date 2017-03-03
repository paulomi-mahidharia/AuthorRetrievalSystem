package com.neu.msd.dblp;

import junit.framework.TestCase;

public class UserTest extends TestCase{
	protected int value1, value2;
	// assigning the values
	   protected void setUp(){
	      value1 = 3;
	      value2 = 3;
	   }
	   
	// test method to add two values
	   public void testSub(){
	      double result = value1 - value2;
	      assertTrue(result == 0);
	   }
}
