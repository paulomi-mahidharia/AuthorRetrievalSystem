package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Conference;

public interface ConferenceService {

	public List<Conference> retrieveAllConferences() throws SQLException;
}
