package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Conference;
import com.neu.msd.AuthorRetriever.service.DatabaseConnection;

public class SeachConferenceDaoImpl implements SearchConferenceDao {

	private Connection conn = DatabaseConnection.getConn();
	
	@Override
	public List<Conference> retrieveConference(String queryString) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(queryString);
		ResultSet rs = stmt.executeQuery();
		
		List<Conference> confs = new ArrayList<Conference>();
		while(rs.next()){
			//populate papers list
		}
		return confs;
	}

}
