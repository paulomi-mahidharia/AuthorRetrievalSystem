package com.neu.msd.AuthorRetriever.model;

import java.util.List;

public class Journal {
	int journalId;
	String journalKey;
	String name;
	String acronym;
	String publisher;
	String issn;
	int volume;
	String url;
	List<Article> articles;
	List<Author> authors;
}
