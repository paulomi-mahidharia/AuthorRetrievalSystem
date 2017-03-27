package com.neu.msd.AuthorRetriever.util;

public class CriteriaUtil {

	public static String equalCriteriaQuery(String tableName, String columnName, String value){
		
		return tableName+"."+ columnName + " = " + "'" + value + "'";
	}
	
	public static String notEqualCriteriaQuery(String tableName, String columnName, String value){
		
		return tableName+"."+ columnName + " <> " + "'" + value + "'";
	}
}
