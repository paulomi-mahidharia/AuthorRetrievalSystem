package com.neu.msd.AuthorRetriever.util;

public class GroupByUtil {

	public static String groupByQuery(String tablename, int numOfPapers, String groupByColumn, String countByColumn) {
		String query = " GROUP BY " + tablename + "." + groupByColumn + " HAVING COUNT(" + tablename + "."
				+ countByColumn + ") >= " + numOfPapers;
		return query;
	}
}
