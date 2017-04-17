package com.neu.msd.DBLPXMLParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.DBLPXMLParser.handler.HandleAuthorHomepages;
import com.neu.msd.DBLPXMLParser.model.AuthorHomepage;

public class AuthorHomepagesParser extends ParserBase {

	private String filePath;

	public AuthorHomepagesParser(String path) {
		filePath = path;
	}

	public static void main(String[] args) {

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
		String filepath = (!filePath.isEmpty() && !filePath.equals(null)) ? filePath : "homepages.csv";

		List<String> linesFromFile = loadLinesFromFile(filepath);
		// Convert the list to an Array of Strings.
		String[] lines = linesFromFile.toArray(new String[0]);
		List<AuthorHomepage> authorHomepagesList = new ArrayList<AuthorHomepage>();

		for (int i = 1; i < lines.length; i++) {
			String[] nameHomepage = lines[i].split(",");

			if (nameHomepage.length == 2) {
				AuthorHomepage authorHomepage = new AuthorHomepage();
				authorHomepage.setName(nameHomepage[0]);
				authorHomepage.setHomepage(nameHomepage[1]);
				authorHomepagesList.add(authorHomepage);
			}
		}

		HandleAuthorHomepages authorHomepages;
		try {
			authorHomepages = new HandleAuthorHomepages();
			authorHomepages.insertRecords(authorHomepagesList);
		} catch (SQLException e) {
			System.out.println("Error processing homepages for authors.");
			e.printStackTrace();
		}

	}
}
