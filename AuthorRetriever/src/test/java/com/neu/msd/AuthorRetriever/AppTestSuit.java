package com.neu.msd.AuthorRetriever;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.*;

import com.neu.msd.AuthorRetriever.dao.SearchAuthorDaoTest;

//JUnit Suite Test
@RunWith(Suite.class)

@SuiteClasses({ 
   UserTest.class, ResultTest.class, SearchAuthorDaoTest.class
})

public class AppTestSuit {
}

