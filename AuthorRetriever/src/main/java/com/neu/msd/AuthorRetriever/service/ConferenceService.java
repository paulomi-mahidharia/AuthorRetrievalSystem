package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Conference;


/**
 * Retrieves list of conferences from database.This method is abstract implementation
 * @Given:This method does not take anything
 * @return: A List of conferences
 *
 */
public interface ConferenceService {

	
	
	public List<Conference> retrieveAllConferences() throws SQLException;
}
