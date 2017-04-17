package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.neu.msd.AuthorRetriever.model.Author;

import junit.framework.TestCase;

public class TestSearchAuthorDao extends TestCase {

	@Test
	public void testSearchAuthorByCriteriaValidQuery(){
		String query = "SELECT * FROM author where author.id = 1048561";
		SearchAuthorDao searchAuthor = new SearchAuthorDaoImpl();
		List<Author> authors= new ArrayList<Author>();
		try{
			authors = searchAuthor.searchAuthorsByCriteria(query);
		}catch(SQLException e){
			assertTrue(true);
		}
		assertEquals(1, authors.size());
	}
	
	@Test
	public void testSearchAuthorByQueryInvalidQuery(){
		//invalid column name
		String query = "SELECT * FROM author where author.author_id = 1048561";
		SearchAuthorDao searchAuthor = new SearchAuthorDaoImpl();
		List<Author> authors= new ArrayList<Author>();
		try{
			authors = searchAuthor.searchAuthorsByCriteria(query);
		}catch(SQLException e){
			assertTrue(true);
		}
		assertEquals(0, authors.size());
	}
	
	@Test
	public void testSearchAuthorByCriteriaNullQuery(){
		String query = null;
		SearchAuthorDao searchAuthor = new SearchAuthorDaoImpl();
		List<Author> authors= new ArrayList<Author>();
		try{
			authors = searchAuthor.searchAuthorsByCriteria(query);
		}catch(SQLException e){
			assertTrue(true);
		}
		assertEquals(0, authors.size());
		
	}
}
