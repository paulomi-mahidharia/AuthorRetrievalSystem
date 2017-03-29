package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.neu.msd.AuthorRetriever.dao.SearchDao;
import com.neu.msd.AuthorRetriever.dao.SearchDaoImpl;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.Paper;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;
import com.neu.msd.AuthorRetriever.model.ServiceInfo;
import com.neu.msd.AuthorRetriever.util.CriteriaUtil;
import com.neu.msd.AuthorRetriever.util.GroupByUtil;
import com.neu.msd.AuthorRetriever.util.PublicationUtil;
import com.neu.msd.AuthorRetriever.util.TitleUtil;
import com.neu.msd.AuthorRetriever.util.YearUtil;

public class SearchServiceImpl implements SearchService {

	private SearchDao searchDao = new SearchDaoImpl();
	
	@Override
	public List<Author> searchAuthorsByCriteria(SearchCriteria criteria) throws SQLException {
		
		String paperQuery = "SELECT author_paper_mapping.Author_Id FROM author_paper_mapping INNER JOIN paper on author_paper_mapping.Paper_Id = paper.paper_id";
		paperQuery += buildPaperQuery(paperQuery, criteria.getPaperInfo());
		// Limiting to 100 Rows for now. 
		String authorPaperQuery = "SELECT author.* FROM author WHERE Id IN (" + paperQuery + ")" + " LIMIT 100";
		

		String editorQuery = "SELECT conference_editor_mapping.editorId FROM conference_editor_mapping INNER JOIN conference on conference_editor_mapping.confId = conference.id";
		editorQuery += buildServiceInfoQuery(editorQuery, criteria.getServiceInfo());
		
		String authConfQuery = "SELECT author.* from author WHERE Id IN (SELECT editor.Author_Id from editor WHERE editor.id IN ("+ editorQuery +"))";
		
		List<Author> paperAuthors = searchDao.searchAuthorsByCriteria(authorPaperQuery);
		List<Author> confAuthors = searchDao.searchAuthorsByCriteria(authConfQuery);
		
		List<Author> authors = processLists(paperAuthors, confAuthors, criteria.isUnion());
		
		//System.out.println(author_ids.toString());
		System.out.println("DONE!");
		return authors;
	}

	private List<Author> processLists(List<Author> paperAuthors, List<Author> confAuthors, boolean union) {
		
		Set<Author> authors = new HashSet<Author>();
		if(union){
			authors.addAll(confAuthors);
			
		}else{
			authors.retainAll(confAuthors);
		}
		return new ArrayList<Author>();
	}

	public static String buildPaperQuery(String query, Paper paper){

		List<String> conditions = new ArrayList<String>();
		
		if(paper.getConferenceName() !=null && !paper.getConferenceName().isEmpty()){
			conditions.add(PublicationUtil.publicationQuery("paper", paper.getConferenceName(), paper.isPublished()));
		}

		if(paper.getKeyword() !=null && !paper.getKeyword().isEmpty()){
			conditions.add(TitleUtil.titleQuery(paper.getKeyword(), "paper", paper.isContains()));
		}
		
		String yearResult = YearUtil.formYearQuery(paper.getOptions(), paper.getStartDate(), paper.getEndDate(), "paper");
		
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
			groupByClause = GroupByUtil.groupByQuery("author_paper_mapping", paper.getNumOfPapersPublished(), "author_id", "paper_id");
		}
		
		return whereCond.toString() + groupByClause;
	}

	public String buildServiceInfoQuery(String query, ServiceInfo serviceInfo) {

		List<String> conditions = new ArrayList<String>();
		
		if(serviceInfo.getConferenceName() !=null && !serviceInfo.getConferenceName().isEmpty()){
			conditions.add(PublicationUtil.conferenceQuery("conference", serviceInfo.getConferenceName(), serviceInfo.isHasServed()));
		}
		
		/*if(serviceInfo.getPosition() !=null && !serviceInfo.getPosition().isEmpty()){
			conditions.add(CriteriaUtil.equalCriteriaQuery("confernece", "name", serviceInfo.getPosition()));
		}*/
		
		//String yearResult = YearUtil.formYearQuery(serviceInfo.getOptions(), serviceInfo.getStartDate(), serviceInfo.getEndDate(), "conference");
		
		/*if(yearResult!= null && !yearResult.isEmpty()){
			   conditions.add(yearResult);	
		}*/
		
		StringBuilder whereCond = conditions.isEmpty() ? new StringBuilder("") : new StringBuilder(" WHERE ");
		for(int i = 0; i<conditions.size() ; i++){
			whereCond.append(conditions.get(i));
			if(i < conditions.size() - 1){
				whereCond.append(" AND ");
			}
		}
		
		return whereCond.toString();
	}
}
