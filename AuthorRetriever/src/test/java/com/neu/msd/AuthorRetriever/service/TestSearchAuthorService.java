package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.Paper;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;
import com.neu.msd.AuthorRetriever.model.ServiceInfo;

import junit.framework.TestCase;

public class TestSearchAuthorService extends TestCase {

	@Test
	public void testSearchAuthorValidCriteria(){
		SearchService searchService = Mockito.mock(SearchService.class);
		searchService = new SearchServiceImpl();
		
		Paper paperInfo = new Paper(2, true, "ecoop", true, "between", 2000, 2011, "Towards");
		
		SearchCriteria searchCritria = new SearchCriteria(paperInfo, null, true);
		List<Author> authors = null;
		try {
			authors = searchService.searchAuthorsByCriteria(searchCritria);
			assertTrue(authors.size() > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			assertNull(authors);
		}
	}
	
	@Test
	public void testSearchAuthorValidCriteria2(){
		SearchService searchService = Mockito.mock(SearchService.class);
		searchService = new SearchServiceImpl();
		
		Paper paperInfo = new Paper(2, true, "ecoop", true, "between", 2000, 2011, "Towards");
		
		SearchCriteria searchCritria = new SearchCriteria(paperInfo, null, true, "soph");
		List<Author> authors = null;
		try {
			authors = searchService.searchAuthorsByCriteria(searchCritria);
			assertTrue(authors.size() > 0);
		} catch (SQLException e) {
			assertNull(authors);
		}
	}
	
	@Test
	public void testSearchAuthorByAuthorName(){
		SearchService searchService = Mockito.mock(SearchService.class);
		searchService = new SearchServiceImpl();
		
		SearchCriteria searchCriteria = new SearchCriteria("Frank Tip");
		List<Author> authors = null;
		try {
			authors = searchService.searchAuthorsByCriteria(searchCriteria);
			assertEquals(authors.size(), 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}
	
	@Test
	public void testSearchAuthorInvalidCriteria(){
		SearchService searchService = Mockito.mock(SearchService.class);
		searchService = new SearchServiceImpl();
		
		Paper paperInfo = new Paper(0, true, "IEEE", true, "between", 0, 0, "");
		SearchCriteria searchCritria = new SearchCriteria(paperInfo, null, true);

		List<Author> authors = null;
		try {
			authors = searchService.searchAuthorsByCriteria(searchCritria);
			System.out.println("AL : "+authors.size());
			assertEquals(authors.size(), 0);
		} catch (SQLException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSearchAuthorNullCriteria(){
		// both criteia null {Unreachable as we have validation in UI} 
		SearchService searchService = Mockito.mock(SearchService.class);
		searchService = new SearchServiceImpl();
		
		SearchCriteria searchCritria = new SearchCriteria(null, null, true);
		List<Author> authors = null;
		try {
			authors = searchService.searchAuthorsByCriteria(searchCritria);
			assertEquals(authors.size(), 0);
		} catch (SQLException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSearchAuthorOnlyPaperCriteria(){
		// service info criteria null
		SearchService searchService = Mockito.mock(SearchService.class);
		searchService = new SearchServiceImpl();
		
		Paper paperInfo = new Paper(1, true, "ecoop", true, "between", 2000, 2011, "");
		SearchCriteria searchCritria = new SearchCriteria(paperInfo, null, true);

		List<Author> authors = null;
		try {
			authors = searchService.searchAuthorsByCriteria(searchCritria);
			System.out.println("AL : "+authors.size());
			assertTrue(authors.size() > 0);
		} catch (SQLException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSearchAuthorOnlyServiceInfoCriteria(){
		// paper criteria null

		SearchService searchService = Mockito.mock(SearchService.class);
		searchService = new SearchServiceImpl();
		
		ServiceInfo serviceInfo = new ServiceInfo(true, "ecoop", "G", "before", 2011, 2011);
		SearchCriteria searchCritria = new SearchCriteria(null, serviceInfo, true);

		List<Author> authors = null;
		try {
			authors = searchService.searchAuthorsByCriteria(searchCritria);
			assertTrue(authors.size() > 0);
		} catch (SQLException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSearchAuthorOnlyServiceInfoCriteriaWithAuthorName(){
		// paper criteria null

		SearchService searchService = Mockito.mock(SearchService.class);
		searchService = new SearchServiceImpl();
		
		ServiceInfo serviceInfo = new ServiceInfo(true, "oopsla", null, "before", 2016, 2016);
		SearchCriteria searchCritria = new SearchCriteria(serviceInfo);
		searchCritria.setAuthorName("Frank Tip");

		List<Author> authors = null;
		try {
			authors = searchService.searchAuthorsByCriteria(searchCritria);
			assertTrue(authors.size() > 0);
		} catch (SQLException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSearchAuthorUnionBetweenCriteria(){
		// OR operation between paper and service info criteria
		
		SearchService searchService = Mockito.mock(SearchService.class);
		searchService = new SearchServiceImpl();
		
		Paper paperInfo = new Paper(1, true, "ecoop", true, "between", 2000, 2011, "");
		ServiceInfo serviceInfo = new ServiceInfo(true, "ecoop", "G", "before", 2011, 2011);
		SearchCriteria searchCritria = new SearchCriteria(paperInfo, serviceInfo, false);

		List<Author> authors = null;
		try {
			authors = searchService.searchAuthorsByCriteria(searchCritria);
			assertTrue(authors.size() > 0);
		} catch (SQLException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSearchAuthorIntersectionBetweenCriteria(){
		// and operation between paper and service info criteria
		SearchService searchService = Mockito.mock(SearchService.class);
		searchService = new SearchServiceImpl();
		
		Paper paperInfo = new Paper(1, true, "ecoop", true, "between", 2000, 2011, "");
		ServiceInfo serviceInfo = new ServiceInfo(true, "ecoop", "G", "before", 2011, 2011);
		SearchCriteria searchCritria = new SearchCriteria(paperInfo, serviceInfo, true);

		List<Author> authors = null;
		try {
			authors = searchService.searchAuthorsByCriteria(searchCritria);
			assertTrue(authors.size() > 0);
		} catch (SQLException e) {
			assertTrue(false);
		}
	}
}
