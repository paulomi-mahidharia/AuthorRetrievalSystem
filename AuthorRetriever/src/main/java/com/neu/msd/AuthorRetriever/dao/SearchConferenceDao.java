package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Conference;



/**
 * The below interface is used to define two methods 
 * 1)Retrieve a list of conference names 
 * 2)Retrieve a list of distinct conference name
 *
 */
public interface SearchConferenceDao {


/**
 * @param A query String to retrieve conferences from database
 * @return A list of conference
 * Retrieve a list of conferences
 */
	
	public List<Conference> retrieveConference(String queryString) throws SQLException;


	/**
	 * @param A query String to retrieve conferences from database
	 * @return A list of conference
	 * Retrieve a list of  distinct conferences
	 */
	
	List<Conference> retrieveDistinctConf(String queryString) throws SQLException;
}
