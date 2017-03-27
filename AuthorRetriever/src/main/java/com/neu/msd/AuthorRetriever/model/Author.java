package com.neu.msd.AuthorRetriever.model;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Inproceeding;

public class Author {
	
	int authorId;
	String authorKey;
	String name;
	int age;
	String degree;
	String gender;
	String country;
	String url;
	List<String> aliasNames;
	List<Inproceeding> inproceedings;
	List<Article> articles;
	List<Thesis> thesis;
	List<Journal> journals;
	
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		Author a = (Author) obj;
		return this.authorId == a.getAuthorId();
	}
}
