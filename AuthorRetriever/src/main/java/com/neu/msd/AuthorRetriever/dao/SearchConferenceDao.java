package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Conference;

public interface SearchConferenceDao {
	
	public List<Conference> retrieveConference(String queryString) throws SQLException;
}
