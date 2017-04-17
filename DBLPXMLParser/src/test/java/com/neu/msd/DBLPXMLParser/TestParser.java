package com.neu.msd.DBLPXMLParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.neu.msd.DBLPXMLParser.config.DBConnection;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class TestParser extends TestCase {

	private static Connection dbConnection;
	private static String FILE_PATH = "test-data/test-dblp.xml";
	private ParserBase parser;

	public void setup() throws SQLException {
		dbConnection = DBConnection.getConn();
	}

	@Test
	public void testParser() throws SQLException {

		setup();

		StandardParserFactory factory = new StandardParserFactory();
		parser = factory.makeDblpParser(FILE_PATH);
		parser.executeParser();
		
		PreparedStatement selectStmt = dbConnection.prepareStatement("select * from proceeding");
		ResultSet rs = selectStmt.executeQuery();
		assertTrue(rs.next());

		selectStmt = dbConnection.prepareStatement("select * from editor_proceeding_mapping");
		rs = selectStmt.executeQuery();
		assertTrue(rs.next());

		selectStmt = dbConnection.prepareStatement("select * from author_paper_mapping");
		rs = selectStmt.executeQuery();
		assertTrue(rs.next());

		selectStmt = dbConnection.prepareStatement("select * from paper");
		rs = selectStmt.executeQuery();
		assertTrue(rs.next());

		selectStmt = dbConnection.prepareStatement("select * from article");
		rs = selectStmt.executeQuery();
		assertTrue(rs.next());

		selectStmt = dbConnection.prepareStatement("select * from author_article_mapping");
		rs = selectStmt.executeQuery();
		assertTrue(rs.next());

		selectStmt = dbConnection.prepareStatement("select * from author_alias");
		rs = selectStmt.executeQuery();
		assertTrue(rs.next());

		selectStmt = dbConnection.prepareStatement("select * from thesis");
		rs = selectStmt.executeQuery();
		assertTrue(rs.next());

		cleanup();
	}

	public void cleanup() {
		try {

			PreparedStatement deleteStmt = dbConnection.prepareStatement("delete from proceeding");

			deleteStmt.execute();

			deleteStmt = dbConnection.prepareStatement("delete from editor_proceeding_mapping");
			deleteStmt.execute();

			deleteStmt = dbConnection.prepareStatement("delete from author_paper_mapping");
			deleteStmt.execute();

			deleteStmt = dbConnection.prepareStatement("delete from paper");
			deleteStmt.execute();

			deleteStmt = dbConnection.prepareStatement("delete from article");
			deleteStmt.execute();

			deleteStmt = dbConnection.prepareStatement("delete from author_article_mapping");
			deleteStmt.execute();

			deleteStmt = dbConnection.prepareStatement("delete from author_alias");
			deleteStmt.execute();

			deleteStmt = dbConnection.prepareStatement("delete from thesis");
			deleteStmt.execute();

		} catch (SQLException e) {
			System.out.println("Error opening connection with database");
			e.printStackTrace();
		}
	}
}
