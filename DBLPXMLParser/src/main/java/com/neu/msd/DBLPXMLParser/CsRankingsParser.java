package com.neu.msd.DBLPXMLParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.DBLPXMLParser.handler.FacultyAffiliation;
import com.neu.msd.DBLPXMLParser.model.AuthorAffiliation;

public class CsRankingsParser {

	public static void main(String[] args) throws SQLException {
		
		// Please update the path for your Faculty Affiliation CSV file here.
		String filepath = "/Users/aswingopalan/Desktop/MSDCode/faculty.csv";
		
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

		FacultyAffiliation facultyAffiliation = new FacultyAffiliation();
		facultyAffiliation.insertRecords(authorAffiliationList);

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
}
