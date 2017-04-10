package com.neu.msd.AuthorRetriever.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.bouncycastle.asn1.isismtt.x509.AdditionalInformationSyntax;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.neu.msd.AuthorRetriever.model.Author;

public class ExportResultPdfImpl implements ExportResult {

	@Override
	public boolean exportResultAsPdf(List<Author> result, File file) {
		// TODO Auto-generated method stub
		
		try {
			Document document =new Document();
			PdfWriter.getInstance(document,new FileOutputStream(file));
			document.open();
			addMetaData(document);
			addTitlePage(document);
			addContent(document);
			document.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	private void addContent(Document document) {
		// TODO Auto-generated method stub
		document.addTitle("Result Information PDF ");
		document.addSubject("Result For Criteria");
		document.addAuthor("UserName");
		document.addCreator("Author");
		
	}

	private void addTitlePage(Document document) {
		// TODO Auto-generated method stub
		
	}

	private void addMetaData(Document document) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exportResultAsEmail(List<Author> result, File file) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
