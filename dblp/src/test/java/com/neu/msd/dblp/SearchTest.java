package com.neu.msd.dblp;

import java.util.List;

import org.junit.Test;

import com.neu.msd.dblp.model.Author;
import com.neu.msd.dblp.service.ResultService;
import com.neu.msd.dblp.service.ResultServiceImpl;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class SearchTest extends TestCase
{
   // test method to add two values
   @Test
   public void testFilterResultNull(){
	   ResultService resultService = new ResultServiceImpl();
	   
	    List<Author> result = resultService.filterResult(null);
      //int result = value1 + value2;
      //System.out.println(result);
	    assertNull(result);
   }
}
