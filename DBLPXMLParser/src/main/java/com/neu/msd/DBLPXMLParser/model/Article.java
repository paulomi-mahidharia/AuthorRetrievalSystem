package com.neu.msd.DBLPXMLParser.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="article")
@XmlAccessorType(XmlAccessType.FIELD)
public class Article {

	@XmlAttribute(name="key")
	String key;
	
	@XmlElement(name="author")
	List<String> authors = new ArrayList<String>();
	
	@XmlElement(name="title")
	String title;
	
	@XmlElement(name="journal")
	String journal;
	
	@XmlElement(name="year")
	String year;
	
	@XmlElement(name="url")
	String url;
	
	@XmlElement(name="crossref")
	String conference;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getConference() {
		return conference;
	}
	public void setConference(String conference) {
		this.conference = conference;
	}
}
