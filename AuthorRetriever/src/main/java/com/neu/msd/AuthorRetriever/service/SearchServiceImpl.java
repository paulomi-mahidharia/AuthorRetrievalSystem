package com.neu.msd.AuthorRetriever.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Paper;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.ServiceInfo;
import com.neu.msd.AuthorRetriever.util.GroupByUtil;
import com.neu.msd.AuthorRetriever.util.PublicationUtil;
import com.neu.msd.AuthorRetriever.util.TitleUtil;
import com.neu.msd.AuthorRetriever.util.YearUtil;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;

public class SearchServiceImpl implements SearchService {

	@Override
	public List<Author> searchAuthorsByCriteria(SearchCriteria criteria) throws SQLException {
		Connection conn = DatabaseConnection.getConn();
		
		
		String query = "SELECT author_paper_mapping.Author_Id FROM author_paper_mapping INNER JOIN paper on author_paper_mapping.Paper_Id = paper.paper_id";
		query += buildPaperQuery(query, criteria.paper_info);
		
		// Limiting to 100 Rows for now. 
		String author_query = "SELECT author.* FROM author WHERE Id IN (" + query + ")" + " LIMIT 100";
		
		PreparedStatement stmt = conn.prepareStatement(author_query);
		ResultSet rs = stmt.executeQuery();
		List<Integer> author_ids = new ArrayList<Integer>();
		
		while(rs.next()){
			author_ids.add(Integer.parseInt(rs.getString(1)));
		}
		
		System.out.println(author_ids.toString());
		System.out.println("DONE!");
		return null;
	}

	@Override
	public List<Author> searchSimilarAuthorProfiles(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String buildPaperQuery(String query, Paper paper){

		List<String> conditions = new ArrayList<String>();
		
		if(paper.getConferenceName() !=null && !paper.getConferenceName().isEmpty()){
			conditions.add(PublicationUtil.publicationQuery("paper", paper.getConferenceName(), paper.isPublished()));
		}

		if(paper.getKeyword() !=null && !paper.getKeyword().isEmpty()){
			conditions.add(TitleUtil.titleQuery(paper.getKeyword(), "paper", paper.contains));
		}
		
		String yearResult = YearUtil.formYearQuery(paper.getOptions(), paper.startDate, paper.endDate, "paper");
		
		if(yearResult!= null && !yearResult.isEmpty()){
		   conditions.add(yearResult);	
		}
		
		StringBuilder whereCond = conditions.isEmpty() ? new StringBuilder("") : new StringBuilder(" WHERE ");
		for(int i = 0; i<conditions.size() ; i++){
			whereCond.append(conditions.get(i));
			if(i < conditions.size() - 1){
				whereCond.append(" AND ");
			}
		}
		
		String groupByClause = "";
		if(paper.getNumOfPapersPublished() > 0){
			groupByClause = GroupByUtil.groupByQuery("author_paper_mapping", paper.numOfPapersPublished, "author_id", "paper_id");
		}
		
		return whereCond.toString() + groupByClause;
	}

	public String BuildServiceInfoQuery(String query, ServiceInfo service_info) {

		return "";
	}

}
