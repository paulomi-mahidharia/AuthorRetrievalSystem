package com.neu.msd.DBLPXMLParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.neu.msd.DBLPXMLParser.config.DBConnection;

import junit.framework.TestCase;

public class TestCSRankingAffiliation extends TestCase {
    
	private static Connection dbConnection;
	private static String FILE_PATH = "test-data/test-faculty.csv";
	private ParserBase parser;
	
	public void setup() throws SQLException{
		dbConnection = DBConnection.getConn();
	}
	
	@Test
    public void testParser() throws SQLException{
		
		setup();
        
		StandardParserFactory factory = new StandardParserFactory();
		parser = factory.makeCsRankingsParser(FILE_PATH);
		parser.executeParser();
		
		PreparedStatement selectStmt = dbConnection.prepareStatement("select * from author_faculty_affiliation");
        ResultSet rs = selectStmt.executeQuery();
        assertTrue(rs.next());
        
        cleanup();
    }	
	
	public void cleanup(){
		try {

			PreparedStatement deleteStmt = dbConnection.prepareStatement("delete from author_faculty_affiliation");
			deleteStmt.execute();
			
			dbConnection.close();
		}catch(SQLException e) {
			System.out.println("Error deleting test records");
			e.printStackTrace();
		}
		
		
	}
}
