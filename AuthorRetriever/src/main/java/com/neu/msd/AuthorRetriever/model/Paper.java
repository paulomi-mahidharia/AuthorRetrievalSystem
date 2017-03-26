package com.neu.msd.AuthorRetriever.model;

import java.util.Date;

public class Paper {
	
	private boolean isPublished;
	private DateOptions options;
	private Date startDate;
	private Date endDate;
	private String keyword;
	private String conference_name;
	
	public String getConference_name() {
		return conference_name;
	}
	public void setConference_name(String conference_name) {
		this.conference_name = conference_name;
	}
	public boolean isPublished() {
		return isPublished;
	}
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	public DateOptions getOptions() {
		return options;
	}
	public void setOptions(DateOptions options) {
		this.options = options;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}

