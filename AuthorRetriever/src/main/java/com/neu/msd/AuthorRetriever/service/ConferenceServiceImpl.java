package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.dao.SearchConferenceDao;
import com.neu.msd.AuthorRetriever.dao.SearchConferenceDaoImpl;
import com.neu.msd.AuthorRetriever.model.Conference;



public class ConferenceServiceImpl implements ConferenceService {

	SearchConferenceDao searchConfDao = new SearchConferenceDaoImpl();
	/**
	 * Retrieves list of conferences from database.
	 * @Given:This method does not take anything
	 * @return: A List of conferences
	 * @exception:SQL Exception
	 */

	@Override
	public List<Conference> retrieveAllConferences() throws SQLException {
		
		String queryString = "SELECT DISTINCT name FROM conference";
		
		List<Conference> conferences = searchConfDao.retrieveDistinctConf(queryString);
		
		return conferences;
		
	}

}
