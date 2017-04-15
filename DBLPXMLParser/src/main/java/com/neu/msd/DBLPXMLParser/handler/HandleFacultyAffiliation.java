package com.neu.msd.DBLPXMLParser.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.DBLPXMLParser.config.DBConnection;
import com.neu.msd.DBLPXMLParser.model.AuthorAffiliation;

public class HandleFacultyAffiliation {

	private Connection conn;
	PreparedStatement stmt_author_faculty_affiliation, stmt_author_paper_mapping;

	public HandleFacultyAffiliation() throws SQLException {
		conn = DBConnection.getConn();
		conn.setAutoCommit(false);
		stmt_author_faculty_affiliation = conn
				.prepareStatement("insert into author_faculty_affiliation(name, affiliation) " + "values (?,?)");
	}

	public boolean insertRecords(List<AuthorAffiliation> authorAffiliation) throws SQLException {

		for (AuthorAffiliation faculty : authorAffiliation) {
			stmt_author_faculty_affiliation.setString(1, faculty.getName());
			stmt_author_faculty_affiliation.setString(2, faculty.getAffiliation());
			stmt_author_faculty_affiliation.addBatch();
		}
		stmt_author_faculty_affiliation.executeBatch();
		conn.commit();
		return true;
	}

}
