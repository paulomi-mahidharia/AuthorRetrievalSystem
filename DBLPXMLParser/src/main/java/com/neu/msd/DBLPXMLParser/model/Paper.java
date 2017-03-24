package com.neu.msd.DBLPXMLParser.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="inproceedings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Paper {
	
	@XmlAttribute(name="key")
	String key;
	
	@XmlElement(name="author")
	List<String> authors = new ArrayList<String>();
	
	@XmlElement(name="title")
	String title;
	
	@XmlElement(name="booktitle")
	String bookTitle;
	
	@XmlElement(name="url")
	String url;
	
	@XmlElement(name="crossref")
	String conference;
	
	@XmlElement(name="year")
	String year;
	
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
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
