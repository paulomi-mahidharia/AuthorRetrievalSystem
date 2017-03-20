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
	
	public SearchCriteria(String keyword, String domain, Date startDate, Date endDate, int papersPublished,
			List<String> conferenceName, List<String> journalName) {
		super();
		this.keyword = keyword;
		this.domain = domain;
		this.startDate = startDate;
		this.endDate = endDate;
		this.papersPublished = papersPublished;
		this.conferenceName = conferenceName;
		this.journalName = journalName;
	}
}
