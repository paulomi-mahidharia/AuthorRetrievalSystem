package com.neu.msd.AuthorRetriever.util;

import com.neu.msd.AuthorRetriever.model.DateOptions;

public class YearUtil {

	public static String formYearQuery(DateOptions type, int start, int end, String tablename) {

		switch (type) {

		case AFTER:
			return tablename + ".year > " + start;

		case BEFORE:
			return tablename + ".year < " + end;

		case BETWEEN:
			return tablename + ".year BETWEEN " + start + " AND " + end;

		default:
			return "";
		}

	}

}
