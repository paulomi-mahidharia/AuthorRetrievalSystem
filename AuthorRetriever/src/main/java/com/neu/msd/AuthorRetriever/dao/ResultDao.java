package com.neu.msd.AuthorRetriever.dao;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.FilterCriteria;

public interface ResultDao {
	public List<Author> filterResult(FilterCriteria criteria, List<Author> result);
}
