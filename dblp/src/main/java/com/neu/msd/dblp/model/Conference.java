package com.neu.msd.dblp.model;

import java.util.List;

public class Conference {
	
	int conferenceId;
	String conferenceKey;
	String name;
	String acronym;
	String detail;
	List<Proceeding> proceedings;
}
