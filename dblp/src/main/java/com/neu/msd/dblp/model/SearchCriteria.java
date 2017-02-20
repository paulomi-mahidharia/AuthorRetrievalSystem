package com.neu.msd.dblp.model;

import java.sql.Date;
import java.util.List;

public class SearchCriteria {
	
	String keyword;
	String domain;
	Date startDate;
	Date endDate;
	int papersPublished;
	List<String> conferenceName;
	List<String> journalName;
}
