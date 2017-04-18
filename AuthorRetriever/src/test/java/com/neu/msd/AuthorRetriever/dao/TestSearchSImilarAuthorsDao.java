package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.AuthorInfoServiceImpl;
import com.neu.msd.AuthorRetriever.service.SearchSimilarProfileServiceImpl;

import junit.framework.TestCase;

public class TestSearchSImilarAuthorsDao extends TestCase {

	public void testSearchSimilarAuthors() throws SQLException {
		AuthorInfoServiceImpl service = new AuthorInfoServiceImpl();
		Author author = service.getAuthorProfile(1369189);
		SearchSimilarProfileServiceImpl similarAuthor = new SearchSimilarProfileServiceImpl();
		int count = similarAuthor.searchSimilarAuthorProfiles(author).size();
		assertTrue(count >= 1);
	}

	@Test
	public void testSearchSimilarAuthorsInvalid() throws SQLException {
		AuthorInfoServiceImpl service = new AuthorInfoServiceImpl();
		Author author = service.getAuthorProfile(1034597);
		SearchSimilarProfileServiceImpl similarAuthor = new SearchSimilarProfileServiceImpl();
		int count = similarAuthor.searchSimilarAuthorProfiles(author).size();
		assertTrue(count == 0);
	}

}
