package com.neu.msd.dblp.Service;

import java.util.List;

/**
 * 
 * @author paulomimahidharia
 *
 */
public interface SearchService {
	
	public List<String> searchInJournal(String journal);
	
	public List<String> searchInConference(String conference);
	
	public List<String> searchInJournalAndConference(String s);
}
