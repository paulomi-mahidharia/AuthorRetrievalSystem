package com.neu.msd.dblp.Service;

import java.util.List;

import com.neu.msd.dblp.model.Author;
import com.neu.msd.dblp.model.SearchCriteria;

/**
 * 
 * @author paulomimahidharia
 *
 */
public interface SearchService {
	
	public List<String> searchInJournal(SearchCriteria criteria);
	
	public List<String> searchInConference(SearchCriteria criteria);
	
	public List<String> searchInJournalAndConference(SearchCriteria criteria);
	
	public List<Author> searchSimilarAuthorProfiles(Author author);
}
