package com.neu.msd.DBLPXMLParser.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="www")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorAlias {
	
	@XmlElement(name="author")
	List<String> authors = new ArrayList<String>();
	

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

}
