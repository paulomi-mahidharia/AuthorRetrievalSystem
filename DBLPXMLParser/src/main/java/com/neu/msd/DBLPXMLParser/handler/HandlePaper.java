package com.neu.msd.DBLPXMLParser.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.DBLPXMLParser.config.DBConnection;
import com.neu.msd.DBLPXMLParser.model.Paper;

public class HandlePaper {
	
	private Connection conn;
	PreparedStatement stmt_paper, stmt_author_paper_mapping;
	
	public HandlePaper() throws SQLException{
		conn = DBConnection.getConn();
		conn.setAutoCommit(false);
		stmt_paper = conn
				.prepareStatement("insert into paper(paper_key, title, book_title, year, conference, url) "
						+ "values (?,?,?,?,?,?)");
		
		stmt_author_paper_mapping = conn
				.prepareStatement("insert into author_paper_mapping(name, paper_key) values (?, ?)");
	}

	public boolean insertPaperRecords(List<Paper> papers) throws SQLException{
		
		for(Paper p: papers){
			stmt_paper.setString(1, p.getKey());
			stmt_paper.setString(2, p.getTitle());
			stmt_paper.setString(3, p.getBookTitle());
			stmt_paper.setInt(4, Integer.parseInt(p.getYear()));
			stmt_paper.setString(5, p.getConference());
			stmt_paper.setString(6, p.getUrl());
			stmt_paper.addBatch();
			
			for(String a: p.getAuthors()){
				stmt_author_paper_mapping.setString(1, a);
				stmt_author_paper_mapping.setString(2, p.getKey());
				stmt_author_paper_mapping.addBatch();
			}
		}
		stmt_paper.executeBatch();
		stmt_author_paper_mapping.executeBatch();
		conn.commit();
		return true;
	}
}
