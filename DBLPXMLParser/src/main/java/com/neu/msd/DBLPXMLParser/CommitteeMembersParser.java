package com.neu.msd.DBLPXMLParser;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.neu.msd.DBLPXMLParser.handler.HandleCommittee;

public class CommitteeMembersParser {

	public static void main(String[] args) {
		
		String filepath = args.length > 0 ? args[0] :"commitees";
		
		File folder = new File("committees");
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i<listOfFiles.length; i++){
			
			try{
				List<String> editors = new ArrayList<String>();
				String name = listOfFiles[i].getName().split("-")[0];
				String confName = name.substring(0, name.length()-4);
				int year = Integer.parseInt(name.substring(name.length()-4));
				Scanner scanner = new Scanner(listOfFiles[i]);
	
				while (scanner.hasNext()){
					editors.add(scanner.nextLine());
				}
				HandleCommittee hc = new HandleCommittee();
				hc.insertCommitteeRecords(editors, confName, year);
			}catch (SQLException e) {
				System.out.println("Error processing committees information for conferences.");
				e.printStackTrace();
			}catch (IOException e) {
				System.out.println("Error processing committees information for conferences.");
				e.printStackTrace();
			}
			
		}
	}
}
