package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.AuthorPaper;

import junit.framework.TestCase;

public class TestAuthorInfoService extends TestCase {

	@Test
	public void testRetrieveAuthorValidAuthorId(){
		// e.g. id = 1
		AuthorInfoService authorInfoService = new AuthorInfoServiceImpl();
		Author author = authorInfoService.getAuthorProfile(1048561);
		System.out.println("AUTH : "+author);
		assertNotNull(author);
	}
	
	@Test
	public void testRetrieveAuthorInvalidAuthorId(){
		// e.g. id = 0
		AuthorInfoService authorInfoService = new AuthorInfoServiceImpl();
		Author author = authorInfoService.getAuthorProfile(0);
		assertNull(author);
	}
	
	@Test
	public void testRetrieveAuthorPapersValidAuthorId(){
		// e.g. id = 1
		AuthorInfoService authorInfoService = new AuthorInfoServiceImpl();
		List<AuthorPaper> papers = null;
		try {
			papers = authorInfoService.getAuthorPapers(1048561);
			assertTrue(papers.size() > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			assertNull(papers);
		}
	}
	
	@Test
	public void testRetrieveAuthorPapersInvalidAuthorId(){
		// e.g. id = 1
		AuthorInfoService authorInfoService = new AuthorInfoServiceImpl();
		List<AuthorPaper> papers = null;
		try {
			papers = authorInfoService.getAuthorPapers(0);
			assertEquals(papers.size(), 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			assertNull(papers);
		}
	}
}
