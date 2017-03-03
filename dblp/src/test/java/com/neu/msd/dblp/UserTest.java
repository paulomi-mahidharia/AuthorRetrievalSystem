package com.neu.msd.dblp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase{
	
	private int value1, value2;
	// assigning the values
	   protected void setUp(){
	      value1 = 3;
	      value2 = 3;
	   }
	   
	// test method to add two values
	   @Test
	   public void testSub(){
	      double result = value1 - value2;
	      assertTrue(result == 0);
	   }
	   
	   @Test
	   public void testMul(){
	      double result = value1 * value2;
	      assertTrue(result == 9);
	   }
}
