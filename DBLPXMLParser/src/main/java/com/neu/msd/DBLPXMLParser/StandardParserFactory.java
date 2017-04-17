package com.neu.msd.DBLPXMLParser;

public class StandardParserFactory extends AbstractParserFactory {

	@Override
	public AuthorHomepagesParser makeAuthorHomePagesParser(String path) {
		return new AuthorHomepagesParser(path);
	}

	@Override
	public CommitteeMembersParser makeCommitteeParser(String path) {
		
		return new CommitteeMembersParser(path);
	}

	@Override
	public CsRankingsParser makeCsRankingsParser(String path) {
		return new CsRankingsParser(path);
	}

	@Override
	public Parser makeDblpParser(String path) {
		return new Parser(path);
	}


}
