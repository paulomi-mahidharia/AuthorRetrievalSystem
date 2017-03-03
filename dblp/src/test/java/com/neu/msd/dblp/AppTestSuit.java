package com.neu.msd.dblp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

public class AppTestSuit {
	//JUnit Suite Test
	@RunWith(Suite.class)

	@Suite.SuiteClasses({ 
	   SearchTest.class, UserTest.class
	})

	public class JunitTestSuite {
	}
}
