package com.neu.msd.DBLPXMLParser;

public abstract class AbstractParserFactory {

	public abstract AuthorHomepagesParser makeAuthorHomePagesParser(String path);

	public abstract CommitteeMembersParser makeCommitteeParser(String path);

	public abstract CsRankingsParser makeCsRankingsParser(String path);

	public abstract Parser makeDblpParser(String path);

}
