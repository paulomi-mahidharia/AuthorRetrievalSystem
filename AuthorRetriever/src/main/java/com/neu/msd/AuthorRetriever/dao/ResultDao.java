package com.neu.msd.AuthorRetriever.dao;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.FilterCriteria;

/**
 * Below interface is used to filter result for user according to filter criteria
 * The interface has following method 
 * filterResult
 * Filter Result Takes following parameters
 * @Given A criteria to search for User and a list of result
 * @Returns A List of author.Author defines all attributes of author stored in database.
 *
 */
public interface ResultDao {
	public List<Author> filterResult(FilterCriteria criteria, List<Author> result);
}
