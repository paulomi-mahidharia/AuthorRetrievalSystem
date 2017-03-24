package com.neu.msd.dblp;

import org.junit.Test;

import com.neu.msd.dblp.model.Author;
import com.neu.msd.dblp.service.AuthorService;
import com.neu.msd.dblp.service.AuthorServiceImpl;

import junit.framework.TestCase;

public class AuthorTest extends TestCase {

	Author author = new Author();
	
	@Test
	public void testInvalidAuthorId(){
		AuthorService authorService = new AuthorServiceImpl();
		assertNull(authorService.getAuthorProfile(-1));
	}
	
	@Test
	public void testValidAuthorId(){
		AuthorService authorService = new AuthorServiceImpl();
		assertNotNull(authorService.getAuthorProfile(1));
	}
}
