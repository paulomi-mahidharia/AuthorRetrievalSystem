package com.neu.msd.AuthorRetriever.service;

import java.io.File;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;

/**
 * This method is a abstract implementation  for exporting  authors to Pdf.
 *  @Given List of Authors,File object to store location of file
 *  @return:A boolean whether file is created or not 
 */	
public interface ExportResult {
	
	public boolean exportResultAsPdf(List<Author> result,File file);
	
	
}
