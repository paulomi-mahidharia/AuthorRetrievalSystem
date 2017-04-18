package com.neu.msd.AuthorRetriever.constants;



/**
 * Vaidation constants that are use to display different validation messages
 *
 */
public class ValidationConstants {
	
	public static final String ALERT_ERROR = "Error";
	public static final String ALERT_HEADER = "Oops, you got soemthing wrong!";
	
	public static final String VALID_CRITERIA = "The Search Criteria is valid";
	public static final String NO_CRITERIA_SELECTED = "Please select criteria based on Paper and/or Service Information";
	
	public static final String INVALID_PAPER_CRITERIA = "Please selects valid Paper Info Criteria";
	public static final String VALID_PAPER_CRITERIA = "The Paper Information criteria is correct";
	
	public static final String INVALID_SERVICE_CRITERIA = "Please selects valid Service Info Criteria";
	public static final String VALID_SERVICE_CRITERIA = "The Service Information criteria is correct";
	
	public static final String INVALID_DATE_RANGE = "Please select a valid date range";
	public static final String INVALID_DATE = "Please select a valid date";
	public static final String INVALID_CREDENTIALS = "Invalid credentials!";
	public static final String VALID_DATE = "Valid date selection";
	public static final String INVALID_NUMBER_OF_PAPERS = "Please enter valid number of publications (0-999)";
	public static final String INVALID_CONFERENCE_NAME = "Please enter a valid conference name";
	public static final String INVALID_KEYWORD = "Please enter a valid keyword or title";
	
	public static final String SQL_FAILURE = "There was problem while retrieving authors. Please try again later.";
	public static final String NO_AUTHORS_FOUND = "No authors found for given search criteria";
	
	public static final String NO_SELECTED_AUTHOR_REMOVE = "Please select an author from the table to remove.";
	public static final String NO_SELECTED_AUTHOR_PROFILE = "Please select an author from the table to view profile";
	
	public static final String ERROR_RETRIEVING_AUTHOR = "An error occur while retrieving author information. Please check your internet connection or try again later.";
	public static final String EMPTY_AUTHOR = "Author name cannot be empty or null";
	public static final String INVALID_AUTHOR = "Invalid author name";
	public static final String VALID_AUHTOR = "Valid author name";
	public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	
	public static final String NO_CONFERENCES_AVAILABLE = "No conferences available";
	public static final String PASSWORD_MISMATCH ="Password and Renter Password  do not match!";
	public static final String INVALID_CRITERIA ="Password doesn't match Password Criteria";
}
