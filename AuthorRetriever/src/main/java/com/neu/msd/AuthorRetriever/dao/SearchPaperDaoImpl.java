package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Paper;
import com.neu.msd.AuthorRetriever.service.DatabaseConnection;

public class SearchPaperDaoImpl implements SearchPaperDao {

	private Connection conn = DatabaseConnection.getConn();
	
	@Override
	public List<Paper> retrievePapers(String queryString) throws SQLException {
		
		PreparedStatement stmt = conn.prepareStatement(queryString);
		ResultSet rs = stmt.executeQuery();
		
		List<Paper> papers = new ArrayList<Paper>();
		while(rs.next()){
			//populate papers list
		}
		return papers;
	}

}
