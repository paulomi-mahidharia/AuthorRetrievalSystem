package dao;

import com.neu.msd.dblp.model.Author;

public class AuthorDaoImpl implements AuthorDao {

	public Author getAuthorById(int authorId) {
		
		if(authorId < 0){
			return null;
		}
		return new Author();
	}

}
