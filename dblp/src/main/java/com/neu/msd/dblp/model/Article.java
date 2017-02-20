package com.neu.msd.dblp.model;

import java.util.Date;
import java.util.List;

public class Article {
	
	int articleId;
	int journalId;
	String articleKey;
	String title;
	int pages;
	Date date;
	int volume;
	String url;
	List<Author> authors;
}
