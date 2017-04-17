package com.neu.msd.AuthorRetriever.util;

import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Conference;



/**
 * The below class returns a list of conferences 
 * @Given:noothing
 * @return:A list of conference
 *  
 *    
 *
 */
public final class ConferenceUtil {
	
	public static List<String> conferences = new ArrayList<>();

	public static List<String> getConferences() {
		return conferences;
	}

	public static void setConferences(List<String> conferences) {
		ConferenceUtil.conferences = conferences;
	}
	
}
