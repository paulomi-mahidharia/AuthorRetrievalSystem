package com.neu.msd.AuthorRetriever.model;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Inproceeding;

public class Author {
	
	private int authorId;
	private String authorKey;
	private String name;
	private int age;
	private String degree;
	private String gender;
	private String country;
	private String url;
	private List<String> aliasNames;
	private List<Inproceeding> inproceedings;
	private List<Article> articles;
	private List<Thesis> thesis;
	private List<Journal> journals;
	
	
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
