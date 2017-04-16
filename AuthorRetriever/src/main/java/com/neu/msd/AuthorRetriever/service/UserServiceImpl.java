	package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.dao.UserDao;
import com.neu.msd.AuthorRetriever.dao.UserDaoImpl;
import com.neu.msd.AuthorRetriever.dao.AddSelectedAuthorsDao;
import com.neu.msd.AuthorRetriever.dao.AddSelectedAuthorsDaoImpl;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.User;

public class UserServiceImpl implements UserService {

	static int loggedInUser;

	UserDao userDao = new UserDaoImpl();

	public static int getLoggedInId() {
		return loggedInUser;
	}

	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		String queryString = "select UserId, Password from UserCredentials where username=?";
		loggedInUser = userDao.login(username, password, queryString);
		return (loggedInUser != 0);
	}
	
	
	public void addSelectedAuthors(List<Author> authors) {
		// TODO Auto-generated method stub
		try {
			AddSelectedAuthorsDao selectedAuthorsDao = new AddSelectedAuthorsDaoImpl();
			selectedAuthorsDao.addSelectedAuthors(loggedInUser, authors);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Author> getAllAuthorsForUser() {

		String queryString = "select author.* from author where id in (select Author_Id from selected_authors where user_Id = ?)";

		//String queryString = "select UserId, Author_Id from selected_authors";

		return userDao.getAuthorsForUser(loggedInUser, queryString);
	}

	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		String queryString= "INSERT INTO UserCredentials"
				+ "(username, Password) VALUES"
				+ "(?,?)";
		int result=userDao.registerUser(queryString, user);
		System.out.println(user.getUsername());
		String queryloginString="select UserId, Password from UserCredentials where username=?";
		
		if ( result == 1){
			loggedInUser = userDao.login(user.getUsername(), user.getPassword(), queryloginString);
			return (loggedInUser != 0);
		}
		return false;
	}

	@Override
	public boolean deleteSelectedAuthor(int authorId) {
		// TODO Auto-generated method stub
		String queryString="Delete from selected_authors where author_Id= ? and user_id = ?";
		
		return userDao.deleteSelectedAuthor(loggedInUser, authorId, queryString);
		
	}
}
