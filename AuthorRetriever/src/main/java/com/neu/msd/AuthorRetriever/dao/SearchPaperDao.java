package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.AuthorPaper;
import com.neu.msd.AuthorRetriever.model.Paper;


/**
 * @param A query String to retrieve a list of AuthorPaper from database
 * @return A list of AuthorPaper Mapping
 * Retrieve a list of  Author Paper mappings from database.This is a abstract implementation of the methods
 */
public interface SearchPaperDao {

	public List<AuthorPaper> retrievePapers(String queryString) throws SQLException;
}
