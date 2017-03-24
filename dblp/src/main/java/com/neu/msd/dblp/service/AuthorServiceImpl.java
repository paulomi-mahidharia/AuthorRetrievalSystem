package com.neu.msd.dblp.service;

import com.neu.msd.dblp.model.Author;

import dao.AuthorDao;
import dao.AuthorDaoImpl;

public class AuthorServiceImpl implements AuthorService {

	public Author getAuthorProfile(int authorId) {
		
		AuthorDao authorDao = new AuthorDaoImpl();
		Author a = null;
		try{
			a = authorDao.getAuthorById(authorId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}

}
