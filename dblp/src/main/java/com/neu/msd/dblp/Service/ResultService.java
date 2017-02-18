package com.neu.msd.dblp.Service;

import java.util.List;

import com.neu.msd.dblp.model.Result;

/**
 * 
 * @author paulomimahidharia
 *
 */
public interface ResultService {
	
	/** A function that filters existing results based on a criteria.
	 * @param criteria - A string to filter results  
	 * @return A list of result objects filtered by the criteria.
	 */
	public List<Result> filterResult(String criteria);
	
	
	/**
	 * @param sortBy - sort results by the string provided
	 * @return - A list of objects sorted by the input string. 
	 */
	public List<Result> sortResult(String sortBy);
	
	/**
	 * @param exportType - A string requesting format of document to be exported to. 
	 * @return - A document in the given format exported from the system to the user's file system.
	 */
	public String exportResult(String exportType);

}
