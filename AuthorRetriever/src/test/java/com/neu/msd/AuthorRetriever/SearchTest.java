package com.neu.msd.AuthorRetriever;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.FilterCriteria;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;
import com.neu.msd.AuthorRetriever.service.ResultService;
import com.neu.msd.AuthorRetriever.service.ResultServiceImpl;
import com.neu.msd.AuthorRetriever.service.SearchService;
import com.neu.msd.AuthorRetriever.service.SearchServiceImpl;

import junit.framework.TestCase;
import static org.junit.Assert.fail;
/**
 * Unit test for simple App.
 */
public class SearchTest extends TestCase
{
	List<Author> result = new ArrayList<Author>();
	
	@Test(expected=SQLException.class)
	public void testSearchAuthorByNullCriteria() throws SQLException{
		SearchService searchService = new SearchServiceImpl();
  		assertNull(searchService.searchAuthorsByCriteria(null));
	}
	@Test(expected=SQLException.class)
	public void testSearchSimilarAuthorCriteriaByAuthorNotInDataBase() throws SQLException{
		SearchService searchService = new SearchServiceImpl();
		Author author=new Author();
		assertEquals(searchService.searchSimilarAuthorProfiles(author).equals(result),result.equals(searchService.searchSimilarAuthorProfiles(author)));
	}
	@Test(expected=NullPointerException.class)
	public void testSearchCriteriaforNullDate()throws SQLException{
		SearchService searchService=new SearchServiceImpl();
		SearchCriteria searchCriteria = Mockito.mock(SearchCriteria.class);
		Mockito.when(searchCriteria.getPaperInfo().getStartDate()).thenReturn(null);
		Mockito.when(searchCriteria.getPaperInfo().getEndDate()).thenReturn(2014);
		Mockito.when(searchCriteria.getPaperInfo().getConferenceName()).thenReturn("lncs");
		assertNotNull(searchService.searchAuthorsByCriteria(searchCriteria));
		
	}
	@Test
	public void testSearchCriteriaBadMinimumPublications() throws SQLException
	{
		SearchService searchService=new SearchServiceImpl();
		SearchCriteria searchCriteria=Mockito.mock(SearchCriteria.class);
		Mockito.when(searchCriteria.getPaperInfo().getConferenceName()).thenReturn("lncs");
		Mockito.when(searchCriteria.getPaperInfo().getEndDate()).thenReturn(2010);
		Mockito.when(searchCriteria.getPaperInfo().getKeyword()).thenReturn("");
		Mockito.when(searchCriteria.getPaperInfo().getNumOfPapersPublished()).thenReturn(4);
		Mockito.when(searchCriteria.getPaperInfo().getStartDate()).thenReturn(2005);
		Mockito.when(searchCriteria.getServiceInfo().getConferenceName()).thenReturn("lncs");
		Mockito.when(searchCriteria.getServiceInfo().isHasServed()).thenReturn(true);
		Mockito.when(searchCriteria.getServiceInfo().getStartDate()).thenReturn(2010);
		Mockito.when(searchCriteria.getServiceInfo().getEndDate()).thenReturn(2015);
		assertNotNull(searchService.searchAuthorsByCriteria(searchCriteria));
		
		
	}
	
   
}
