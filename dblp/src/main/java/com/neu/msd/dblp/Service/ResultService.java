package com.neu.msd.dblp.Service;

import java.util.List;

import com.neu.msd.dblp.model.FilterCriteria;
import com.neu.msd.dblp.model.Result;

/**
 * 
 * @author paulomimahidharia
 *
 */
public interface ResultService {
	
<<<<<<< HEAD
	/**
	 * Filters result based on given filter criteria
	 * @param criteria criteria for filtering results
	 * @return list of result records 
	 */
	public List<Result> filterResult(FilterCriteria criteria);
	
	/**
	 * Sorts by the given element (column name in result table)
	 * @param sortBy element to sort by
	 * @return list of result records
=======
	/** A function that filters existing results based on a criteria.
	 * @param criteria - A string to filter results  
	 * @return A list of result objects filtered by the criteria.
	 */
	public List<Result> filterResult(String criteria);
	
	
	/**
	 * @param sortBy - sort results by the string provided
	 * @return - A list of objects sorted by the input string. 
>>>>>>> 43de0c3b2d64a317a9e311e522f854fc746c95c0
	 */
	public List<Result> sortResult(String sortBy);
	
	/**
<<<<<<< HEAD
	 * Exports result in PDF, CSV, or other format
	 * @param exportType file type to export to
	 * @return success/failure message
=======
	 * @param exportType - A string requesting format of document to be exported to. 
	 * @return - A document in the given format exported from the system to the user's file system.
>>>>>>> 43de0c3b2d64a317a9e311e522f854fc746c95c0
	 */
	public String exportResult(String exportType);

}
