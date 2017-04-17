package com.neu.msd.AuthorRetriever.service;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.FilterCriteria;

import com.neu.msd.AuthorRetriever.dao.ResultDao;
import com.neu.msd.AuthorRetriever.dao.ResultDaoImpl;

public class ResultServiceImpl implements ResultService{

	/**
	 * Filters result based on given filter criteria
	 * @param criteria criteria for filtering results
	 * @return list of result records 
	 */
	
	public List<Author> filterResult(FilterCriteria criteria, List<Author> result) {
		// TODO Auto-generated method stub

		ResultDao resultDao = new ResultDaoImpl();
		List<Author> authors = null;
		try{
			authors = resultDao.filterResult(criteria, result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return authors;
	}

	/**
	 * Sorts by the given element (column name in result table)
	 * @param sortBy element to sort by
	 * @return list of result records
	 */
	
	public List<Author> sortResult(String resultAttribute, List<Author> result) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Exports result in PDF, CSV, or other format
	 * @param exportType - A string requesting format of document to be exported to
	 * @return success/failure message
	 */
	
	public String exportResult(String exportType, List<Author> result) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
