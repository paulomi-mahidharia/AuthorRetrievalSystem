package com.neu.msd.dblp.model;

import java.sql.Date;
import java.util.List;

public class Proceeding {
	
	int proceedingId;
	int conferenceId;
	int bookId;
	String proceedingKey;
	String name;
	String title;
	Date date;
	String location;
	String url;
	List<Inproceeding> inproceedings;
}
