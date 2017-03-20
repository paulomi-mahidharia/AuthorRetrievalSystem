package com.neu.msd.dblp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilterCriteria {
	
	Date startDate;
	Date endDate;
	String displayStartDate;
	String displayEndDate;
	String location;
	String gender;
	String fieldOfService;
	int numOfResults;
	List<Integer> conferenceIds;
	Boolean includeConferences;
	List<Integer> journalIds;
	Boolean includeJournals;
	
	public FilterCriteria() {
		
		this.displayStartDate = "";
		this.displayEndDate = "";
		this.location = "";
		this.gender = "";
		this.fieldOfService = "";
		this.numOfResults = 0;
		this.conferenceIds = new ArrayList<Integer>();
		this.includeConferences = false;
		this.journalIds = new ArrayList<Integer>();
		this.includeJournals = false;
	}

	public FilterCriteria(String displayStartDate, String displayEndDate, String location,
			String gender, String fieldOfService, int numOfResults, List<Integer> conferenceIds,
			Boolean includeConferences, List<Integer> journalIds, Boolean includeJournals) {
		super();
		this.displayStartDate = displayStartDate;
		this.displayEndDate = displayEndDate;
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
	
	public String getDisplayStartDate() {
		return displayStartDate;
	}

	public void setDisplayStartDate(String displayStartDate) {
		this.displayStartDate = displayStartDate;
		
		if(displayEndDate != null){ 
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
			try {
				if(displayStartDate == "") this.setStartDate(new Date());
				this.setStartDate(fmt.parse(displayStartDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getDisplayEndDate() {
		return displayEndDate;
	}

	public void setDisplayEndDate(String displayEndDate) {
		this.displayEndDate = displayEndDate;
		
		if(displayEndDate != null){ 
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
			try {
				if(displayEndDate == "") this.setStartDate(new Date());
				else this.setEndDate(fmt.parse(displayEndDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
