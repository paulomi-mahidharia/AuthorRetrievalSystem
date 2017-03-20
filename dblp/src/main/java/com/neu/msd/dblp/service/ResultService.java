package com.neu.msd.dblp.service;

import java.util.List;

import com.neu.msd.dblp.model.Author;
import com.neu.msd.dblp.model.FilterCriteria;

/**
 * 
 * @author paulomimahidharia
 *
 */
public interface ResultService {
	
	/**
	 * Filters result based on given filter criteria
	 * @param criteria criteria for filtering results
	 * @return list of result records 
	 */
	public List<Author> filterResult(FilterCriteria criteria, List<Author> result);
	
	/**
	 * Sorts by the given element (column name in result table)
	 * @param sortBy element to sort by
	 * @return list of result records
	 */
	public List<Author> sortResult(String resultAttribute, List<Author> result);
	
	/**
	 * Exports result in PDF, CSV, or other format
	 * @param exportType - A string requesting format of document to be exported to
	 * @return success/failure message
	 */
	public String exportResult(String exportType, List<Author> result);

}
