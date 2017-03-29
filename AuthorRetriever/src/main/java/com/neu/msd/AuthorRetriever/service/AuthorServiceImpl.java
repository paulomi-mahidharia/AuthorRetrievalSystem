package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.dao.SearchAuthorDao;
import com.neu.msd.AuthorRetriever.dao.SearchAuthorDaoImpl;
import com.neu.msd.AuthorRetriever.dao.SearchPaperDao;
import com.neu.msd.AuthorRetriever.dao.SearchPaperDaoImpl;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.AuthorPaper;

public class AuthorServiceImpl implements AuthorService {

	private SearchPaperDao searchPaperDao = new SearchPaperDaoImpl();
	
	@Override
	public Author getAuthorProfile(int authorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthorPaper> getAuthorPapers(int authorId) throws SQLException {
		
		List<AuthorPaper> authorPapers = new ArrayList<AuthorPaper>();
		
		String queryString = "SELECT * FROM paper WHERE paper.paper_id IN (SELECT paper_Id FROM author_paper_mapping WHERE author_paper_mapping.paper_id = "+authorId+")";
		
		authorPapers = searchPaperDao.retrievePapers(queryString);
		
		return authorPapers;
	}

}
