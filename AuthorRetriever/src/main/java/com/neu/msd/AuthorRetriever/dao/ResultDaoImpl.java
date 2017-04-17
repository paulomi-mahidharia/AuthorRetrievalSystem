package com.neu.msd.AuthorRetriever.dao;

import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.FilterCriteria;



/**
 * The below class is use to return a list of authors according to filter criteria .This class is a concrete implementation for ResultDao interface
 * The class consist of following methods 
 * fiterResult 
 * @Given a Criteria to filter Author objects and a list of results
 * @return A list of authors according to user specified criteria
 *
 */
public class ResultDaoImpl implements ResultDao{

	public List<Author> filterResult(FilterCriteria criteria, List<Author> result) {
		
		List<Author> filteredAuthors = null;
		
		if(criteria != null){
			filteredAuthors = new ArrayList<Author>();
		}
		return filteredAuthors;
	}
	
}
