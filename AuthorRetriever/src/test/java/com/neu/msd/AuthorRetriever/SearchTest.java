package com.neu.msd.AuthorRetriever;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.ResultService;
import com.neu.msd.AuthorRetriever.service.ResultServiceImpl;
import com.neu.msd.AuthorRetriever.service.SearchService;
import com.neu.msd.AuthorRetriever.service.SearchServiceImpl;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class SearchTest extends TestCase
{
	List<Author> result = new ArrayList<Author>();
	
	@Test
	public void testFilterResultForNullCriteria() throws SQLException{
		SearchService searchService = new SearchServiceImpl();
  		assertNull(searchService.searchAuthorsByCriteria(null));
	}
   
}
