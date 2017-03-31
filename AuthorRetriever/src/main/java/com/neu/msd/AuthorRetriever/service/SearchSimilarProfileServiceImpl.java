package com.neu.msd.AuthorRetriever.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.dao.SearchSimilarAuthorsDao;
import com.neu.msd.AuthorRetriever.dao.SearchSimilarAuthorsDaoImpl;
import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
import com.neu.msd.AuthorRetriever.model.Author;

public class SearchSimilarProfileServiceImpl implements SearchSimilarProfileService {

	SearchSimilarAuthorsDao searchdao = new SearchSimilarAuthorsDaoImpl();
	
	@Override
	public List<Author> searchSimilarAuthorProfiles(Author author) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DatabaseConnection.getConn();
		String query1PaperQuery = java.text.MessageFormat.format("SELECT author_paper_mapping.Paper_Id FROM author_paper_mapping WHERE author_paper_mapping.Author_Id = {0}", Long.toString(author.getAuthorId()));
		String query1AuthorQuery = java.text.MessageFormat.format("SELECT author_paper_mapping.Author_Id FROM author_paper_mapping WHERE author_paper_mapping.Paper_Id IN (" + query1PaperQuery + ")" + " AND author_paper_mapping.Author_Id <> {0}",Long.toString(author.getAuthorId())) ;
		
		String query2Editor = java.text.MessageFormat.format("SELECT editorId from conference_editor_mapping join editor on conference_editor_mapping.editorId = editor.Id WHERE editor.Author_Id = {0}", Long.toString(author.getAuthorId()));
		String query2EditorQuery = "SELECT editorId FROM conference_editor_mapping WHERE confId IN (" + query2Editor + ")";
		String query2AuthorQuery = java.text.MessageFormat.format("SELECT Author_Id FROM editor where Id IN (" + query2EditorQuery + ")" +  " AND editor.Author_Id <> {0}",Long.toString(author.getAuthorId()));
		
		String finalQuery = query1AuthorQuery + " UNION " + query2AuthorQuery;
		
		return searchdao.SearchSimilarAuthors(finalQuery);

	}

}
