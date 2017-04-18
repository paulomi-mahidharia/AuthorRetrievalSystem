package com.neu.msd.AuthorRetriever.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Constants that define different scenes attributes
 *
 */
public class SceneContants {
	
	public static final String SEARCH = "search";
	public static final String LOGIN = "login";
	public static final String RESULT = "result";
	public static final String AUTHOR = "author";
	public static final String REGISTER = "Register";
	public static final String LOGIN_TITLE = "AUTHOR RETRIEVAL SYSTEM";
	public static final String SEARCH_TITLE = "SEARCH AUTHORS";
	public static final String RESULT_TITLE = "RESULT";
	public static final String AUTHOR_TITLE = "AUTHOR PROFILE";
	public static final String REGISTER_TITLE = "REGISTER USER";
	public static final String FONT_TYPE = "Tahoma";
	public static final String AUTHORNAME = "Author Name";
	public static final String SERIALNO = "Serial No.";
	public static final String UNIVERSITY = "University";
	public static final String URL = "URL";
	public static final String YEAR = "Year";
	public static final String NAME = "Name";
	public static final String TITLE = "Title";
	public static final String CONFERENCE_TITLE ="Conferance Title";
	public static final String PAPER_TITLE="Paper Title";
	public static final int TITLE_FONT_SIZE = 24;
	public static final int HEADER_FONT_SIZE = 20;
	
	public static final double SCENE_LENGTH = 1000;
	public static final double SCENE_WIDTH = 720;
	
	public static final double SCENE_GRID_GAP = 10;
	public static final double SCENE_GRID_PADDING = 25;
	
	public static final double PROGRESS_INDICATOR_DIMENSION = 150;
	
	/**
	 * LOGIN SCENE CONTROLS
	 */
	public static final String USER_NAME = "Username:";
	public static final String PROMPT_USER_NAME = "Enter username:";
	public static final String PASSWORD = "Password:";
	public static final String PROMPT_PASSWORD = "Enter password:";
	
	/**
	 * SEARCH SCENE CONTROLS
	 */
	//Constants for paper criteria
	public static final String SEARCH_PAPER_INFO = "Search based on paper information";
	public static final String NUM_OF_PUBLICATIONS = "Number of minimum Publications:";
	public static final String PROMPT_NUM_OF_PUBLICATIONS = "Number of papers/articles";
	public static final String CONFERENCE_PUBLISHED = "Conference published/not published for:";
	public static final List<String> PUBLISH_OPTIONS = new ArrayList<>(Arrays.asList( "Published in", "Not published in"));
	public static final String YEAR_RANGE = "Year range:";
	public static final List<String> YEAR_RANGE_OPTIONS = new ArrayList<>(Arrays.asList("before", "between", "after"));
	public static final String PROMPT_FROM_YEAR = "From year";
	public static final String TO_LABEL = "to";
	public static final String PROMPT_TO_YEAR = "To year";
	public static final String KEYWORD_LABEL = "Keyword or Title:";
	public static final String PROMPT_KEYWORD = "Enter keyword or title";
	
	//Constants for service criteria
	public static final String SEARCH_SERVICE_INFO = "Search based on service information";
	public static final String CONFERENCE_SERVED = "Conference served/not served in:";
	public static final List<String> SERVED_OPTIONS = new ArrayList<>(Arrays.asList( "Served in", "Not served in"));
	public static final String POSITION_SERVED = "Position served as:";
	public static final List<String> POSITION_OPTIONS = new ArrayList<>(Arrays.asList("All", "General Chair", "Program Chair", "Conference Chair","External Review Committee"));
	
	//Constants for author criteria
	public static final String SEARCH_AUTHOR_INFO = "Search based on author name";
	public static final String AUTHOR_NAME = "Author Name:";
	public static final String PROMPT_AUTHO_NAME = "Enter author name";
}
