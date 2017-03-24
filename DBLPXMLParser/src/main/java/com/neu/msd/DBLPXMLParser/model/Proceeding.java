package com.neu.msd.DBLPXMLParser.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="proceedings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Proceeding {

	@XmlAttribute(name="key")
	String key;
	
	@XmlElement(name="editor")
	List<String> editors = new ArrayList<String>();
	
	@XmlElement(name="title")
	String title;
	
	@XmlElement(name="booktitle")
	String bookTitle;
	
	@XmlElement(name="isbn")
	String isbn;
	
	@XmlElement(name="year")
	String year;

	@XmlElement(name="url")
	String url;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getEditors() {
		return editors;
	}

	public void setEditors(List<String> editors) {
		this.editors = editors;
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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
}
