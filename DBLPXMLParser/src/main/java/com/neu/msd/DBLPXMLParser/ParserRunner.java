package com.neu.msd.DBLPXMLParser;

public class ParserRunner {

	public static void main(String[] args) {
		ParserBase parser;
		StandardParserFactory factory = new StandardParserFactory();

		// Execute the DBLP Parser
		parser = factory.makeDblpParser("dblp.xml");
		parser.executeParser();

		// Execute the Committee Parser
		parser = factory.makeCommitteeParser("committees");
		parser.executeParser();

		// Execute CS Rankings Parser
		parser = factory.makeCsRankingsParser("faculty.csv");
		parser.executeParser();

		// Execute Author Home page Parser
		parser = factory.makeAuthorHomePagesParser("homepages.csv");
		parser.executeParser();

	}
}
