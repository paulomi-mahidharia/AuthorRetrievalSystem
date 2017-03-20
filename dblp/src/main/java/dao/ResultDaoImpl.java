package dao;

import java.util.ArrayList;
import java.util.List;

import com.neu.msd.dblp.model.Author;
import com.neu.msd.dblp.model.FilterCriteria;

public class ResultDaoImpl implements ResultDao{

	public List<Author> filterResult(FilterCriteria criteria, List<Author> result) {
		
		List<Author> filteredAuthors = null;
		
		if(criteria != null){
			filteredAuthors = new ArrayList<Author>();
		}
		return filteredAuthors;
	}
	
}
