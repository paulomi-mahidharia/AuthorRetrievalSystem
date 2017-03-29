package com.neu.msd.AuthorRetriever.model;

import java.util.Date;

public class ServiceInfo {

	private boolean hasServed;
	private String conferenceName;
	private String position;
	private String options;
	private int startDate;
	private int endDate;
	
	public boolean isHasServed() {
		return hasServed;
	}
	public void setHasServed(boolean hasServed) {
		this.hasServed = hasServed;
	}
	public String getConferenceName() {
		return conferenceName;
	}
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
}
