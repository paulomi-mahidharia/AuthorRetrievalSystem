package com.neu.msd.AuthorRetriever.dao;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;

public interface AuthorDao {

	public List<Author> getAuthorByAuthorId(int authorId) throws SQLException;
}
