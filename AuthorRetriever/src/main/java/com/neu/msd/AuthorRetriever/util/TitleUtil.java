package com.neu.msd.AuthorRetriever.util;

public class TitleUtil {

	public static String titleQuery(String title, String tableName,boolean contains){
		return tableName + ".title LIKE '%" +  title + "%'" ;
	}
	
	
}
