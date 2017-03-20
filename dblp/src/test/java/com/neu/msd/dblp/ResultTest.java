package com.neu.msd.dblp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.neu.msd.dblp.model.Author;
import com.neu.msd.dblp.model.FilterCriteria;
import com.neu.msd.dblp.service.ResultService;
import com.neu.msd.dblp.service.ResultServiceImpl;

import junit.framework.TestCase;

public class ResultTest extends TestCase{
	
	List<Author> result = new ArrayList<Author>();
	
	@Test
	public void testFilterResultForNullCriteria(){
		ResultService resultService = new ResultServiceImpl();
  		assertNull(resultService.filterResult(null, result));
	}
	
	@Test
	public void testFilterResultForOnlyInitializedCriteria(){
		ResultService resultService = new ResultServiceImpl();
		FilterCriteria filterCriteria = Mockito.mock(FilterCriteria.class);
		Mockito.when(filterCriteria).thenReturn(new FilterCriteria());
  		assertNull(resultService.filterResult(filterCriteria, result));
	}
	
	@Test
	public void testFilterResultForAllNullCriteria(){
		ResultService resultService = new ResultServiceImpl();
		FilterCriteria filterCriteria = Mockito.mock(FilterCriteria.class);
		
		Mockito.when(filterCriteria.getStartDate()).thenReturn(null);
		Mockito.when(filterCriteria.getEndDate()).thenReturn(null);
		Mockito.when(filterCriteria.getDisplayStartDate()).thenReturn(null);
		Mockito.when(filterCriteria.getDisplayEndDate()).thenReturn(null);
		Mockito.when(filterCriteria.getLocation()).thenReturn(null);
		Mockito.when(filterCriteria.getGender()).thenReturn(null);
		Mockito.when(filterCriteria.getFieldOfService()).thenReturn(null);
		Mockito.when(filterCriteria.getNumOfResults()).thenReturn(null);
		Mockito.when(filterCriteria.getConferenceIds()).thenReturn(null);
		Mockito.when(filterCriteria.getIncludeConferences()).thenReturn(null);
		Mockito.when(filterCriteria.getJournalIds()).thenReturn(null);
		Mockito.when(filterCriteria.getIncludeJournals()).thenReturn(null);
  		assertNull(resultService.filterResult(filterCriteria, result));
	}
		
	@Test
	public void testFilterResultWithValidStartDateNoEndDate(){
	
		ResultService resultService = new ResultServiceImpl();
	  	FilterCriteria filterCriteria = Mockito.mock(FilterCriteria.class);
	  	
	  	Mockito.when(filterCriteria.getDisplayStartDate()).thenReturn("01/01/2017");
	  	Mockito.when(filterCriteria.getDisplayEndDate()).thenReturn(null);
	  	assertNotNull(resultService.filterResult(filterCriteria, result));
	}
	
	@Test
	public void testFilterResultWithValidDateRange(){
	
		ResultService resultService = new ResultServiceImpl();
	  	FilterCriteria filterCriteria = Mockito.mock(FilterCriteria.class);
	  	
	  	Mockito.when(filterCriteria.getDisplayStartDate()).thenReturn("01/01/2017");
	  	Mockito.when(filterCriteria.getDisplayEndDate()).thenReturn("01/30/2017");
	  	assertNotNull(resultService.filterResult(filterCriteria, result));
	}
	
	@Test
	public void testFilterResultWithNoStartDateValidEndDate(){
	
		ResultService resultService = new ResultServiceImpl();
	  	FilterCriteria filterCriteria = Mockito.mock(FilterCriteria.class);
	  	
	  	Mockito.when(filterCriteria.getDisplayStartDate()).thenReturn(null);
	  	Mockito.when(filterCriteria.getDisplayEndDate()).thenReturn("01/01/2017");
	  	assertNull(resultService.filterResult(filterCriteria, result));
	}
	
	@Test
	public void testFilterResultWithInvalidDateRange(){
	
		ResultService resultService = new ResultServiceImpl();
	  	FilterCriteria filterCriteria = Mockito.mock(FilterCriteria.class);
	  	
	  	Mockito.when(filterCriteria.getDisplayStartDate()).thenReturn("01/30/2017");
	  	Mockito.when(filterCriteria.getDisplayEndDate()).thenReturn("01/01/2017");
	  	assertNull(resultService.filterResult(filterCriteria, result));
	}
	
	@Test
	public void testFilterResultWithFutureStartDate(){
	
		ResultService resultService = new ResultServiceImpl();
	  	FilterCriteria filterCriteria = Mockito.mock(FilterCriteria.class);
	  	
	  	Mockito.when(filterCriteria.getDisplayStartDate()).thenReturn("01/01/2050");
	  	assertNull(resultService.filterResult(filterCriteria, result));
	}
	
	@Test
	public void testFilterResultWithOneValidCriteria(){
	
		ResultService resultService = new ResultServiceImpl();
	  	FilterCriteria filterCriteria = Mockito.mock(FilterCriteria.class);
	  	
	  	Mockito.when(filterCriteria.getGender()).thenReturn("male");
	  	assertNotNull(resultService.filterResult(filterCriteria, result));
	}
	
	@Test
	public void testFilterResultWithAtleastTwoValidCriteria(){
	
		ResultService resultService = new ResultServiceImpl();
	  	FilterCriteria filterCriteria = Mockito.mock(FilterCriteria.class);
	  	
	  	Mockito.when(filterCriteria.getGender()).thenReturn("male");
	  	Mockito.when(filterCriteria.getLocation()).thenReturn("Boston");
	  	Mockito.when(filterCriteria.getDisplayStartDate()).thenReturn("01/01/2017");
	  	assertNotNull(resultService.filterResult(filterCriteria, result));
	}
	

}
