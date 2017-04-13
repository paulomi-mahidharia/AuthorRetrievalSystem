package com.neu.msd.AuthorRetriever.util;

public class PublicationUtil {

	public static String publicationQuery(String tableName, String conference, boolean isPublished) {

		String constraint = isPublished ? "IN" : "NOT IN";
		return tableName + ".conference_name " + constraint + " (" + conference + ")";
	}
	
	public static String conferenceQuery(String tableName, String conference, boolean isPublished) {

		String constraint = isPublished ? "IN" : "NOT IN";
		return tableName + ".name " + constraint + " ('" + conference + "')";
	}
}
