package com.neu.msd.dblp.model;

import java.sql.Date;
import java.util.List;

public class FilterCriteria {
	
	Date startDate;
	Date endDate;
	String location;
	String gender;
	String fieldOfService;
	int numOfResults;
	List<Integer> conferenceIds;
	Boolean includeConferences;
	List<Integer> journalIds;
	Boolean includeJournals;
}
