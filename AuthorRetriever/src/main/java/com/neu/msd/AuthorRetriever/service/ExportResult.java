package com.neu.msd.AuthorRetriever.service;

import java.io.File;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;

public interface ExportResult {
	
	public boolean exportResultAsPdf(List<Author> result,File file);
	
	public boolean exportResultAsEmail(List<Author> result,File file);

}
