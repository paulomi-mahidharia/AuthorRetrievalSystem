	package com.neu.msd.AuthorRetriever.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.dao.UserDao;
import com.neu.msd.AuthorRetriever.dao.UserDaoImpl;
import com.neu.msd.AuthorRetriever.dao.SelectedAuthorsDao;
import com.neu.msd.AuthorRetriever.dao.SelectedAuthorsDaoImpl;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.User;

public class UserServiceImpl implements UserService {

	static int loggedInUser;

	UserDao userDao = new UserDaoImpl();

	public static int getLoggedInId() {
		return loggedInUser;
	}

	/**
	 * User credentials to login to the system
	 * 
	 * @param Name
	 *            of the user
	 * @param password
	 *            of the user
	 * @return If the user has valid credentials to access the system.
	 */
	
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		String queryString = "select UserId, Password from UserCredentials where username=?";
		loggedInUser = userDao.login(username, password, queryString);
		return (loggedInUser != 0);
	}
	
	/**
	 * 
	 * Add all selected authors to database
	 * @Given:A list of short listed authors
	 * @Return:Void
	 *
	 */
	
	
	public void addSelectedAuthors(List<Author> authors) {

		try {
			SelectedAuthorsDao selectedAuthorsDao = new SelectedAuthorsDaoImpl();
			selectedAuthorsDao.addSelectedAuthors(loggedInUser, authors);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets a list of short listed authors
	 * @Given:Void
	 * @return:Returns a list of author
	 *
	 */
	
	
	public List<Author> getAllAuthorsForUser() {
		SelectedAuthorsDao selectedAuthorsDao;
		List<Author> selectedAuthors = new ArrayList<Author>();
		try{
			selectedAuthorsDao = new SelectedAuthorsDaoImpl();
			selectedAuthors = selectedAuthorsDao.getSelectedAuthorsForUser(loggedInUser);
		}catch(SQLException e){
			System.out.println("Unable to fetch selected authors for user "+ loggedInUser);
		}

		return selectedAuthors;
	}

	/**
	 * Register User to system.
	 * @Given:A user object that is used to store user credentials in DB
	 * @return:A boolean
	 *
	 */
	
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

	/**
	 * Deleted Selected Author from short listed author 
	 * @Given:A authorId which is integer
	 * @return:A boolean
	 *
	 */
	
	@Override
	public boolean deleteSelectedAuthor(int authorId) {
		boolean status;
		try {
			SelectedAuthorsDao selectedAuthorsDao = new SelectedAuthorsDaoImpl();
			status = selectedAuthorsDao.deleteSelectedAuthors(loggedInUser, authorId);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return status;
		
	}
}
