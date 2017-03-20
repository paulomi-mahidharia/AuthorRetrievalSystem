package dao;

import java.util.List;

import com.neu.msd.dblp.model.Author;
import com.neu.msd.dblp.model.FilterCriteria;

public interface ResultDao {
	public List<Author> filterResult(FilterCriteria criteria, List<Author> result);
}
