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
	
	public FilterCriteria(Date startDate, Date endDate, String location, String gender, String fieldOfService,
			int numOfResults, List<Integer> conferenceIds, Boolean includeConferences, List<Integer> journalIds,
			Boolean includeJournals) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.gender = gender;
		this.fieldOfService = fieldOfService;
		this.numOfResults = numOfResults;
		this.conferenceIds = conferenceIds;
		this.includeConferences = includeConferences;
		this.journalIds = journalIds;
		this.includeJournals = includeJournals;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFieldOfService() {
		return fieldOfService;
	}

	public void setFieldOfService(String fieldOfService) {
		this.fieldOfService = fieldOfService;
	}

	public int getNumOfResults() {
		return numOfResults;
	}

	public void setNumOfResults(int numOfResults) {
		this.numOfResults = numOfResults;
	}

	public List<Integer> getConferenceIds() {
		return conferenceIds;
	}

	public void setConferenceIds(List<Integer> conferenceIds) {
		this.conferenceIds = conferenceIds;
	}

	public Boolean getIncludeConferences() {
		return includeConferences;
	}

	public void setIncludeConferences(Boolean includeConferences) {
		this.includeConferences = includeConferences;
	}

	public List<Integer> getJournalIds() {
		return journalIds;
	}

	public void setJournalIds(List<Integer> journalIds) {
		this.journalIds = journalIds;
	}

	public Boolean getIncludeJournals() {
		return includeJournals;
	}

	public void setIncludeJournals(Boolean includeJournals) {
		this.includeJournals = includeJournals;
	}
}
