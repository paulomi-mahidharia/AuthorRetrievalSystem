package com.neu.msd.DBLPXMLParser.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.DBLPXMLParser.config.DBConnection;
import com.neu.msd.DBLPXMLParser.model.MasterThesis;
import com.neu.msd.DBLPXMLParser.model.Thesis;

public class HandleThesis {

	private Connection conn;
	PreparedStatement stmt_thesis;
	
	public HandleThesis() throws SQLException{
		conn = DBConnection.getConn();
		conn.setAutoCommit(false);
		stmt_thesis = conn
				.prepareStatement("insert into thesis(thesis_key, author, title, school, year, url, type) "
						+ "values (?,?,?,?,?,?,?)");
		
		}

	public boolean insertThesisRecords(List<Thesis> thesis) throws SQLException{
		
		for(Thesis t: thesis){
			
			int year = 0;
			try{
				year = Integer.parseInt(t.getYear());
			}catch(NumberFormatException e){
				System.out.println("Error parsing year:"+ t.getYear() +" in article: "+t.getKey());
			}
			for(String a: t.getAuthors()){
				stmt_thesis.setString(1, t.getKey());
				stmt_thesis.setString(2, a);
				stmt_thesis.setString(3, t.getTitle());
				stmt_thesis.setString(4, t.getSchool());
				stmt_thesis.setInt(5, year);
				stmt_thesis.setString(6, t.getUrl());
				stmt_thesis.setString(7, t instanceof MasterThesis ? "master" : "phd");
				stmt_thesis.addBatch();
			}
			
		}
		stmt_thesis.executeBatch();
		conn.commit();
		return true;
	}
}
