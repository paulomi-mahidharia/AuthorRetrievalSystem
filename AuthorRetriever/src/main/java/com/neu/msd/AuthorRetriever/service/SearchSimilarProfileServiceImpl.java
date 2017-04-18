package com.neu.msd.AuthorRetriever.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.neu.msd.AuthorRetriever.dao.SearchSimilarAuthorsDao;
import com.neu.msd.AuthorRetriever.dao.SearchSimilarAuthorsDaoImpl;
import com.neu.msd.AuthorRetriever.database.config.DatabaseConnection;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.util.CriteriaUtil;

public class SearchSimilarProfileServiceImpl implements SearchSimilarProfileService {

	SearchSimilarAuthorsDao searchdao = new SearchSimilarAuthorsDaoImpl();

	/**
	 * Search for authors who have a matching profile as the given author
	 * 
	 * @param author
	 *            author whose matching profile is to be found
	 * @return list of authors with matching profile
	 * @throws SQLException
	 */

	@Override
	public List<Author> searchSimilarAuthorProfiles(Author author) throws SQLException {
		// TODO Auto-generated method stub
		List<Author> authorList = new ArrayList<Author>();
		if (author != null) {
			Connection conn = DatabaseConnection.getConn();
			String query1PaperQuery = java.text.MessageFormat.format(
					"SELECT author_paper_mapping.Paper_Id FROM author_paper_mapping WHERE author_paper_mapping.Author_Id = {0}",
					Long.toString(author.getAuthorId()));
			String query1AuthorQuery = java.text.MessageFormat.format(
					"SELECT author_paper_mapping.Author_Id FROM author_paper_mapping WHERE author_paper_mapping.Paper_Id IN ("
							+ query1PaperQuery + ")" + " AND author_paper_mapping.Author_Id <> {0}",
					Long.toString(author.getAuthorId()));

			String query2Editor = java.text.MessageFormat.format(
					"SELECT editorId from conference_editor_mapping join editor on conference_editor_mapping.editorId = editor.Id WHERE editor.Author_Id = {0}",
					Long.toString(author.getAuthorId()));
			String query2EditorQuery = "SELECT editorId FROM conference_editor_mapping WHERE confId IN (" + query2Editor
					+ ")";
			String query2AuthorQuery = java.text.MessageFormat.format("SELECT Author_Id FROM editor where Id IN ("
					+ query2EditorQuery + ")" + " AND editor.Author_Id <> {0}", Long.toString(author.getAuthorId()));

			// String finalQuery = "SELECT * FROM author where author.id IN (" +
			// query1AuthorQuery + " UNION " + query2AuthorQuery +")";

			String paperQuery = "SELECT * FROM author where author.id IN (" + query1AuthorQuery + ")";
			String editorQuery = "SELECT * FROM author where author.id IN (" + query2AuthorQuery + ")";

			List<Author> l1 = searchdao.searchSimilarAuthors(paperQuery);
			List<Author> l2 = searchdao.searchSimilarAuthors(editorQuery);

			Set<Author> finalList = new HashSet<Author>();
			finalList.addAll(l1);
			finalList.addAll(l2);

			authorList = new ArrayList<Author>(finalList);
			Collections.sort(authorList);

		}

		return authorList;
	}

}
