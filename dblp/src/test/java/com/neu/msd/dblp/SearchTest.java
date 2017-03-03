package com.neu.msd.dblp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class SearchTest extends TestCase
{
	private int value1, value2;
	
	  // assigning the values
	   protected void setUp(){
	      value1 = 3;
	      value2 = 3;
	   }

	   // test method to add two values
	   @Test
	   public void testAdd(){
	      int result = value1 + value2;
	      System.out.println(result);
	      assertTrue(result == 6);
	   }
	   
	
}
