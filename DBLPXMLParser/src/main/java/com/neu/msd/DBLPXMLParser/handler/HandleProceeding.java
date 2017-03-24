package com.neu.msd.DBLPXMLParser.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.DBLPXMLParser.config.DBConnection;
import com.neu.msd.DBLPXMLParser.model.Proceeding;

public class HandleProceeding {
	
	private Connection conn;
	PreparedStatement stmt_proceeding, stmt_editor_proceeding_mapping;
	
	public HandleProceeding() throws SQLException{
		conn = DBConnection.getConn();
		conn.setAutoCommit(false);
		stmt_proceeding = conn
				.prepareStatement("insert into proceeding(proceeding_key, title, book_title, isbn, year, url) "
						+ "values (?,?,?,?,?,?)");
		
		stmt_editor_proceeding_mapping = conn
				.prepareStatement("insert into editor_proceeding_mapping(name, proceeding_key) values (?, ?)");
	}

	public boolean insertProceedingRecords(List<Proceeding> proceedings) throws SQLException{
		
		for(Proceeding p: proceedings){
			stmt_proceeding.setString(1, p.getKey());
			stmt_proceeding.setString(2, p.getTitle());
			stmt_proceeding.setString(3, p.getBookTitle());
			stmt_proceeding.setString(4, p.getIsbn());
			stmt_proceeding.setInt(5, Integer.parseInt(p.getYear()));
			stmt_proceeding.setString(6, p.getUrl());
			stmt_proceeding.addBatch();
			
			for(String e: p.getEditors()){
				stmt_editor_proceeding_mapping.setString(1, e);
				stmt_editor_proceeding_mapping.setString(2, p.getKey());
				stmt_editor_proceeding_mapping.addBatch();
			}
		}
		stmt_proceeding.executeBatch();
		stmt_editor_proceeding_mapping.executeBatch();
		conn.commit();
		return true;
	}
}
