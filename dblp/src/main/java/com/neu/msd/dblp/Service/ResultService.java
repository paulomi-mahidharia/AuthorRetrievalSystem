package com.neu.msd.dblp.Service;

import java.util.List;

import com.neu.msd.dblp.model.Result;

/**
 * 
 * @author paulomimahidharia
 *
 */
public interface ResultService {
	
	public List<Result> filterResult(String criteria);
	
	public List<Result> sortResult(String sortBy);
	
	public String exportResult(String exportType);

}
