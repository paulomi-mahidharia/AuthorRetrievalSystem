package com.neu.msd.AuthorRetriever.service;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.FilterCriteria;

import com.neu.msd.AuthorRetriever.dao.ResultDao;
import com.neu.msd.AuthorRetriever.dao.ResultDaoImpl;

public class ResultServiceImpl implements ResultService{

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

	public List<Author> sortResult(String resultAttribute, List<Author> result) {
		// TODO Auto-generated method stub
		return null;
	}

	public String exportResult(String exportType, List<Author> result) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
