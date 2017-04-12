package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
import com.neu.msd.AuthorRetriever.model.AuthorPaper;
import com.neu.msd.AuthorRetriever.model.Conference;

public class SearchConferenceDaoImpl implements SearchConferenceDao {

	private Connection conn = DatabaseConnection.getConn();

	@Override
	public List<Conference> retrieveConference(String queryString) throws SQLException {
		System.out.println(queryString);
		PreparedStatement stmt = conn.prepareStatement(queryString);
		ResultSet rs = stmt.executeQuery();
		stmt.setQueryTimeout(300);

		List<Conference> confs = new ArrayList<Conference>();
		while (rs.next()) {
			Conference c = new Conference();
			c.setConferenceId(Integer.parseInt(rs.getString(1)));
			c.setName(rs.getString(2));
			c.setYear(Integer.parseInt(rs.getString(5)));

			confs.add(c);
		}
		return confs;
	}

}
