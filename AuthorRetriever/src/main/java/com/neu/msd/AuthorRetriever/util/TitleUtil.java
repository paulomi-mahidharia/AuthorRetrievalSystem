package com.neu.msd.AuthorRetriever.util;

public class TitleUtil {

	public static String titleQuery(String title, String tableName, boolean contains){
		
		return contains ? tableName + ".title LIKE '%" +  title + "%'" 
				        : tableName + ".title NOT LIKE '%" +  title + "%'" ;
	
	}
	
	
}
