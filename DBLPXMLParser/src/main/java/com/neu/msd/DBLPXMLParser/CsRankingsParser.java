package com.neu.msd.DBLPXMLParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.neu.msd.DBLPXMLParser.handler.HandleFacultyAffiliation;
import com.neu.msd.DBLPXMLParser.model.AuthorAffiliation;

public class CsRankingsParser extends ParserBase {

	private String filePath;

	public CsRankingsParser(String path) {
		filePath = path;
	}

	// Loader routine that picks up the file from the specified path
	public static List<String> loadLinesFromFile(String path) {
		Path Filepath = Paths.get(path);
		try {
			List<String> lines = Files.readAllLines(Filepath);
			return lines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}

	@Override
	public void executeParser() {
		// Please update the path for your Faculty Affiliation CSV file here.
		String filepath = (!filePath.isEmpty() && !filePath.equals(null)) ? filePath : "faculty.csv";

		List<String> linesFromFile = loadLinesFromFile(filepath);
		// Convert the list to an Array of Strings.
		String[] lines = linesFromFile.toArray(new String[0]);
		List<AuthorAffiliation> authorAffiliationList = new ArrayList<AuthorAffiliation>();

		for (int i = 1; i < lines.length; i++) {
			String[] nameAffiliation = lines[i].split(",");

			if (nameAffiliation.length == 2) {
				AuthorAffiliation authorAffiliation = new AuthorAffiliation();
				authorAffiliation.setName(nameAffiliation[0]);
				authorAffiliation.setAffiliation(nameAffiliation[1]);
				authorAffiliationList.add(authorAffiliation);
			}
		}

		HandleFacultyAffiliation facultyAffiliation;
		try {
			facultyAffiliation = new HandleFacultyAffiliation();
			facultyAffiliation.insertRecords(authorAffiliationList);
		} catch (SQLException e) {
			System.out.println("Error while processing faculty affilications.");
			e.printStackTrace();
		}

	}
}
