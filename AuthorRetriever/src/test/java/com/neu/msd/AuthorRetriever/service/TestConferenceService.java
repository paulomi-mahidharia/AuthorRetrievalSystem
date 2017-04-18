package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.neu.msd.AuthorRetriever.dao.SearchConferenceDao;
import com.neu.msd.AuthorRetriever.dao.SearchConferenceDaoImpl;
import com.neu.msd.AuthorRetriever.model.Conference;

import junit.framework.TestCase;

public class TestConferenceService extends TestCase {

	@Test
	public void testRetrieveDistinctConfs() {

		String query = "select DISTINCT(name) from conference";
		SearchConferenceDao searchConf = new SearchConferenceDaoImpl();
		List<Conference> confs = new ArrayList<Conference>();
		try {
			confs = searchConf.retrieveDistinctConf(query);
		} catch (SQLException e) {
			assertTrue(true);
		}
		assertEquals(12, confs.size());

	}
}
