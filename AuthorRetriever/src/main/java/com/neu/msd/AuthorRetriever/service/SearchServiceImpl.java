package com.neu.msd.AuthorRetriever.service;

import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.dao.SearchDao;
import com.neu.msd.AuthorRetriever.dao.SearchDaoImpl;
import com.neu.msd.AuthorRetriever.dao.UserDao;
import com.neu.msd.AuthorRetriever.dao.UserDaoImpl;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;

public class SearchServiceImpl implements SearchService{

	@Override
	public List<Author> searchAuthorsByCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		SearchDao serviceDao = new SearchDaoImpl();
		List<Author> authors = null;
		try{
			authors = new ArrayList<>();
			authors =  serviceDao.searchAuthorsByCriteria(criteria);
		}catch(Exception e){
			e.printStackTrace();
		}
		return authors;
	}

	@Override
	public List<Author> searchSimilarAuthorProfiles(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

}
