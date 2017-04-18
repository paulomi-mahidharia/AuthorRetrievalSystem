package com.neu.msd.AuthorRetriever.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.UserServiceImpl;

import junit.framework.TestCase;

public class TestSelectedAuthorsDao extends TestCase {

	@Test
	public void testAddSelectedAuthor() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.login("a", "a");
		int count = userService.getAllAuthorsForUser().size();
		List<Author> authors = new ArrayList<Author>();
		Author author = new Author();
		author.setAuthorId(1048563);
		authors.add(author);
		userService.addSelectedAuthors(authors);
		assertEquals(userService.getAllAuthorsForUser().size(), count + 1);
		userService.deleteSelectedAuthor(1048563);
	}

	@Test
	public void testRetrieveSelectedAuthors() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.login("a", "a");
		int count = userService.getAllAuthorsForUser().size();
		assertTrue(count >= 0);
	}

	@Test
	public void testDeleteSelectedAuthors() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.login("a", "a");
		int count = userService.getAllAuthorsForUser().size();
		List<Author> authors = new ArrayList<Author>();
		Author author = new Author();
		author.setAuthorId(1048563);
		authors.add(author);
		userService.addSelectedAuthors(authors);
		userService.deleteSelectedAuthor(1048563);
		assertEquals(userService.getAllAuthorsForUser().size(), count);
	}
}
