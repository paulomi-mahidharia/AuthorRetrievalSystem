package com.neu.msd.AuthorRetriever.service;

import org.junit.Test;

import junit.framework.TestCase;

public class TestSearchAuthorService extends TestCase {

	@Test
	public void testSearchAuthorValidCriteria(){
		
	}
	
	@Test
	public void testSearchAuthorInvalidCriteria(){
		
	}
	
	@Test
	public void testSearchAuthorNullCriteria(){
		// both criteia null {Unreachable as we have validation in UI} 
	}
	
	@Test
	public void testSearchAuthorOnlyPaperCriteria(){
		// service info criteria null
	}
	
	@Test
	public void testSearchAuthorOnlyServiceInfoCriteria(){
		// paper criteria null
	}
	
	@Test
	public void testSearchAuthorUnionBetweenCriteria(){
		// OR operation between paper and service info criteria
	}
	
	@Test
	public void testSearchAuthorIntersectionBetweenCriteria(){
		// and operation between paper and service info criteria
	}
	
	// more cases go here
}
