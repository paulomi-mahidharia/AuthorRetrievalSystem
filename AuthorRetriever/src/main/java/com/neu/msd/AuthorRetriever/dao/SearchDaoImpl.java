package com.neu.msd.AuthorRetriever.dao;

import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;

public class SearchDaoImpl implements SearchDao{

	@Override
	public List<Author> searchAuthorsByCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		List<Author> authors = new ArrayList<>();
		System.out.println("HURRAY:::"+criteria.isUnion());
		return authors;
	}

	@Override
	public List<Author> searchSimilarAuthorProfiles(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

}
