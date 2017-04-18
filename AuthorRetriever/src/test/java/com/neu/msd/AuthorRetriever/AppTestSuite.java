package com.neu.msd.AuthorRetriever;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.neu.msd.AuthorRetriever.config.TestDatabaseConnection;
import com.neu.msd.AuthorRetriever.dao.TestSearchAuthorDao;
import com.neu.msd.AuthorRetriever.dao.TestSearchConferenceDao;
import com.neu.msd.AuthorRetriever.dao.TestSearchPaperDao;
import com.neu.msd.AuthorRetriever.dao.TestSearchSImilarAuthorsDao;
import com.neu.msd.AuthorRetriever.dao.TestSelectedAuthorsDao;
import com.neu.msd.AuthorRetriever.dao.TestUserDao;
import com.neu.msd.AuthorRetriever.service.TestAuthorInfoService;
import com.neu.msd.AuthorRetriever.service.TestConferenceService;
import com.neu.msd.AuthorRetriever.service.TestExportResultService;
import com.neu.msd.AuthorRetriever.service.TestSearchAuthorService;
import com.neu.msd.AuthorRetriever.service.TestSearchSimilarProfileService;
import com.neu.msd.AuthorRetriever.service.TestUserService;

//JUnit Suite Test
@RunWith(Suite.class)

@SuiteClasses({ 
   TestSearchAuthorDao.class, TestSearchConferenceDao.class, 
   TestSearchPaperDao.class, TestUserDao.class,
   TestSelectedAuthorsDao.class, TestSearchSImilarAuthorsDao.class,
   TestAuthorInfoService.class, TestSearchAuthorService.class, 
   TestSearchSimilarProfileService.class, TestUserService.class,
   TestConferenceService.class,
   TestDatabaseConnection.class, TestExportResultService.class   
})
public class AppTestSuite {
}

