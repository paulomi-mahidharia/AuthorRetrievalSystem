package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.neu.msd.AuthorRetriever.model.Conference;

import junit.framework.TestCase;

public class TestSearchConferenceDao extends TestCase {

	@Test
	public void testSearchConferenceCriteriaValidQuery(){
		
		String query = "select * from conference where conference.id = 373";
		SearchConferenceDao searchConf = new SearchConferenceDaoImpl();
		List<Conference> confs = new ArrayList<Conference>();
		try{
			confs = searchConf.retrieveConference(query);
		}catch(SQLException e){
			assertFalse(true);
		}
		assertEquals(1, confs.size());
	}
	
	@Test
	public void testSearchConferenceCriteriaInvalidQuery(){
		
		// invalid column name
		String query = "select * from conference where conference.conf_id = 373";
		SearchConferenceDao searchConf = new SearchConferenceDaoImpl();
		List<Conference> confs = new ArrayList<Conference>();
		try{
			confs = searchConf.retrieveConference(query);
		}catch(SQLException e){
			assertTrue(true);
		}
		assertEquals(0, confs.size());
	}
	
	@Test
	public void testSearchConferenceCriteriaNullQuery(){
		
		String query = null;
		SearchConferenceDao searchConf = new SearchConferenceDaoImpl();
		List<Conference> confs = new ArrayList<Conference>();
		try{
			confs = searchConf.retrieveConference(query);
		}catch(SQLException e){
			assertTrue(true);
		}
		assertEquals(0, confs.size());
	}
	
	@Test
	public void testRetrieveDistinctConf(){
		String queryString = "SELECT DISTINCT name FROM conference";
		SearchConferenceDao searchConfDao = new SearchConferenceDaoImpl();
		List<Conference> confs = new ArrayList<Conference>();
		try{
			confs = searchConfDao.retrieveDistinctConf(queryString);
		}catch(SQLException e){
			assertTrue(false);
		}
		assertTrue(confs.size() > 0);
		assertEquals(12, confs.size()); // for now there are only 12 distinct confs
	}
}
