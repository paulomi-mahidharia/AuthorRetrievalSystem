package com.neu.msd.AuthorRetriever.model;

import java.util.Date;

public class Paper {
	public String conferenceName;
	public boolean isPublished;
	public boolean contains;
	public String options;
	public int startDate;
	public int endDate;
	public int numOfPapersPublished;
	
	public Paper(){
		this.conferenceName = "";
		this.numOfPapersPublished = 0;
		this.startDate = 0;
		this.endDate = 0;
		this.options = "";
	}
	
	
	public int getNumOfPapersPublished() {
		return numOfPapersPublished;
	}

	public void setNumOfPapersPublished(int numOfPapersPublished) {
		this.numOfPapersPublished = numOfPapersPublished;
	}

	public boolean isContains() {
		return contains;
	}

	public void setContains(boolean contains) {
		this.contains = contains;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String keyword;
}
