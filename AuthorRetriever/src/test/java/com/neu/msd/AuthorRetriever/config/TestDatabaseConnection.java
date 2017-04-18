package com.neu.msd.AuthorRetriever.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;

import junit.framework.TestCase;

public class TestDatabaseConnection extends TestCase {

	@Test
	public void testDbConn() throws SQLException{
		Connection dbConnection = DatabaseConnection.getConn();
		PreparedStatement selectStmt = dbConnection.prepareStatement("select count(*) from proceeding");
		ResultSet rs = selectStmt.executeQuery();
		assertTrue(rs.first());
	}
}
