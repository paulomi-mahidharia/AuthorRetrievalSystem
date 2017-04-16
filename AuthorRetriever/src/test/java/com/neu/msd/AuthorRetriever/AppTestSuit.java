package com.neu.msd.AuthorRetriever;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.neu.msd.AuthorRetriever.dao.TestSearchAuthorDao;
import com.neu.msd.AuthorRetriever.dao.TestSearchConferenceDao;
import com.neu.msd.AuthorRetriever.dao.TestSearchPaperDao;
import com.neu.msd.AuthorRetriever.dao.TestUserDao;
import com.neu.msd.AuthorRetriever.service.TestAuthorInfoService;
import com.neu.msd.AuthorRetriever.service.TestResultService;
import com.neu.msd.AuthorRetriever.service.TestSearchAuthorService;
import com.neu.msd.AuthorRetriever.service.TestSearchSimilarProfileService;
import com.neu.msd.AuthorRetriever.service.TestUserService;

//JUnit Suite Test
@RunWith(Suite.class)

@SuiteClasses({ 
<<<<<<< HEAD
   TestSearchAuthorDao.class, TestSearchConferenceDao.class, 
   TestSearchPaperDao.class, TestUserDao.class,
   TestAuthorInfoService.class, TestSearchAuthorService.class, 
   TestSearchSimilarProfileService.class, TestUserService.class,
   TestResultService.class
=======
   UserTest.class, ResultTest.class,SearchTest.class
>>>>>>> 527780e7be8c5f2bd5d6f1108f869725165d3735
})

public class AppTestSuit {
}

