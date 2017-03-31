package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.neu.msd.AuthorRetriever.model.Author;


import junit.framework.TestCase;

public class TestSearchSimilarProfileService extends TestCase {

	@Test 
	public void testSimilarAuthors(){
		SearchSimilarProfileService searchService = Mockito.mock(SearchSimilarProfileService.class);
		searchService = new SearchSimilarProfileServiceImpl();
		
		Author author = new Author();
		author.setAuthorId(706483);

		List<Author> authors = null;
		try {
			authors = searchService.searchSimilarAuthorProfiles(author);
			assertTrue(authors.size() == 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			assertNull(authors);
		}
	}
}
