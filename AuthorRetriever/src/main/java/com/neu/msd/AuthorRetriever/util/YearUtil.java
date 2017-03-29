package com.neu.msd.AuthorRetriever.util;

import com.neu.msd.AuthorRetriever.model.DateOptions;

public class YearUtil {

	public static String formYearQuery(String type, int start, int end, String tablename) {

		if (type == null) {
			type = "before";
		}
		
		switch (type) {

		case "after":
			return tablename + ".year > " + start;

		case "before":
			return tablename + ".year < " + end;

		case "between":
			return tablename + ".year BETWEEN " + start + " AND " + end;

		default:
			return tablename + ".year < " + end;
		}

	}

}
