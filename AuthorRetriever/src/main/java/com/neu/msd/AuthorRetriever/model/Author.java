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
	private String affiliation;
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

	public String getAuthorKey() {
		return authorKey;
	}

	public void setAuthorKey(String authorKey) {
		this.authorKey = authorKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public List<String> getAliasNames() {
		return aliasNames;
	}

	public void setAliasNames(List<String> aliasNames) {
		this.aliasNames = aliasNames;
	}

	public List<Inproceeding> getInproceedings() {
		return inproceedings;
	}

	public void setInproceedings(List<Inproceeding> inproceedings) {
		this.inproceedings = inproceedings;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Thesis> getThesis() {
		return thesis;
	}

	public void setThesis(List<Thesis> thesis) {
		this.thesis = thesis;
	}

	public List<Journal> getJournals() {
		return journals;
	}

	public void setJournals(List<Journal> journals) {
		this.journals = journals;
	}

	@Override
	public boolean equals(Object obj) {
		Author a = (Author) obj;
		return this.authorId == a.getAuthorId();
	}

}
