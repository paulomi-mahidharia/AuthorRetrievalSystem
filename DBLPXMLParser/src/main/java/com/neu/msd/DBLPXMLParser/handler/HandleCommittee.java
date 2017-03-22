package com.neu.msd.DBLPXMLParser.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.jsoup.Jsoup;

import com.neu.msd.DBLPXMLParser.config.DBConnection;

public class HandleCommittee {

	private Connection conn;
	PreparedStatement committee;
	
	public HandleCommittee() throws SQLException{
		conn = DBConnection.getConn();
		conn.setAutoCommit(false);
		committee = conn
				.prepareStatement("insert into committee(conf_name, year, editor, position) "
						+ "values (?,?,?,?)");
		
		}

	public boolean insertCommitteeRecords(List<String> editors, String confName, int year) throws SQLException{
		
		for(String e: editors){
			
			String ed = null;
			String pos = null;
			String[] split = e.split(":");
			if(split.length > 1){
				pos = split[0];
				ed = split[1];
			}else{
				ed = split[0];
			}
			
			committee.setString(1, confName);
			committee.setInt(2, year);
			committee.setString(3, Jsoup.parse(ed).text());
			committee.setString(4, pos);
			committee.addBatch();
			
		}
		committee.executeBatch();
		conn.commit();
		return true;
	}
}
