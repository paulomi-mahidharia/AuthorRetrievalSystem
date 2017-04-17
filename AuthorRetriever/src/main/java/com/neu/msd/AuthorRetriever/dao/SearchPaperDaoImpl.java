package com.neu.msd.AuthorRetriever.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.AuthorPaper;
import com.neu.msd.AuthorRetriever.model.Paper;


/**
 * This class is use to implement SearchPaperDao to retrieve List of Author Paper mapping objects 
 *
 */
public class SearchPaperDaoImpl implements SearchPaperDao {

	/**
	 * @param A query String to retrieve a list of AuthorPaper from database
	 * @return A list of AuthorPaper Mapping
	 * Retrieve a list of  Author Paper mappings from database.
	 */

	private Connection conn = DatabaseConnection.getConn();
	
	@Override
	public List<AuthorPaper> retrievePapers(String queryString) throws SQLException {
		
		PreparedStatement stmt = conn.prepareStatement(queryString);
		ResultSet rs = stmt.executeQuery();
		
		List<AuthorPaper> papers = new ArrayList<AuthorPaper>();
		while(rs.next()){
			AuthorPaper p = new AuthorPaper();
			p.setPaperId(Integer.parseInt(rs.getString(1)));
			p.setPaperTitle(rs.getString(3));
			p.setYear(Integer.parseInt(rs.getString(5)));
			p.setConfName(rs.getString(8));
			p.setUrl(rs.getString(7));
			
			papers.add(p);
		}
		return papers;
	}

}
