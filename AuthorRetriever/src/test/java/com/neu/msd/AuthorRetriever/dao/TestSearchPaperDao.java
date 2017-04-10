package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.neu.msd.AuthorRetriever.model.AuthorPaper;

import junit.framework.TestCase;

public class TestSearchPaperDao extends TestCase {

	@Test
	public void testSearchPaperCriteriaValidQuery(){
		
		String query = "select * from paper where paper.paper_id = 1";
		SearchPaperDao searchPaper = new SearchPaperDaoImpl();
		List<AuthorPaper> papers = new ArrayList<AuthorPaper>();
		try{
			papers = searchPaper.retrievePapers(query);
		}catch(SQLException e){
			assertFalse(true);
		}
		assertEquals(1, papers.size());
	}
	
	@Test
	public void testSearchPaperCriteriaInvalidQuery(){
		//query with invalid column name
		String query = "select * from paper where paper.id = 0";
		SearchPaperDao searchPaper = new SearchPaperDaoImpl();
		List<AuthorPaper> papers = new ArrayList<AuthorPaper>();
		try{
			papers = searchPaper.retrievePapers(query);
		}catch(SQLException e){
			assertTrue(true);
		}
		assertEquals(0, papers.size());
	}
	
	@Test
	public void testSearchPaperCriteriaNullQuery(){
		String query = null;
		SearchPaperDao searchPaper = new SearchPaperDaoImpl();
		List<AuthorPaper> papers = new ArrayList<AuthorPaper>();
		try{
			papers = searchPaper.retrievePapers(query);
		}catch(SQLException e){
			assertTrue(true);
		}
		assertEquals(0, papers.size());
	}
	
}
