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
}
