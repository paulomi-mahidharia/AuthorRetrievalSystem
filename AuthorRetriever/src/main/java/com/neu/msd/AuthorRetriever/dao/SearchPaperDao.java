package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Paper;

public interface SearchPaperDao {

	public List<Paper> retrievePapers(String queryString) throws SQLException;
}
