package com.neu.msd.dblp.service;

public interface ImportService {

	/**
	 * Parses and Imports the given dataset into database.
	 * @param dataset : The dataset can be in any format such as XML or JSON
	 * @return boolean value true when import is successfully
	 */
	public boolean parseAndImportDataset(String dataset);
}
