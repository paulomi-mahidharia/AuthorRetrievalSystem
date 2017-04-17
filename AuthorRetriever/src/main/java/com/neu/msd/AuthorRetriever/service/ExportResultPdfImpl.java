package com.neu.msd.AuthorRetriever.service;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.bouncycastle.asn1.isismtt.x509.AdditionalInformationSyntax;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.neu.msd.AuthorRetriever.model.Author;

public class ExportResultPdfImpl implements ExportResult {
	 private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
             Font.BOLD);
private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
             Font.NORMAL, BaseColor.RED);
private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
             Font.BOLD);
private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
             Font.BOLD);


/**
 * This method is a  implementation  for exporting  authors to Pdf.
 *  @Given List of Authors,File object to store location of file
 *  @return:A boolean whether file is created or not 
 */	

	@Override
	public boolean exportResultAsPdf(List<Author> result, File file) {
		// TODO Auto-generated method stub
		
		try {
			Document document =new Document();
			PdfWriter.getInstance(document,new FileOutputStream(file));
			document.open();
			addMetaData(document);
			addTitlePage(document);
			addContent(document,result);
			document.close();
			return true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
	}

	private void addContent(Document document,List<Author> result) {
		// TODO Auto-generated method stub
		Anchor anchor = new Anchor("", catFont);
        anchor.setName("Result  Table");

		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		//Paragraph subPara = new Paragraph("", subFont);
		//Section subCatPart = catPart.addSection(subPara);
		
		createTable(catPart,result);
		 try {
			document.add(catPart);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addTitlePage(Document document) {
		// TODO Auto-generated method stub
		 Paragraph preface = new Paragraph();
         // We add one empty line
         addEmptyLine(preface, 1);
         // Lets write a big header
         preface.add(new Paragraph("Result For Author Retrival", catFont));
         try {
			document.add(preface);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addMetaData(Document document) {
		document.addTitle("Result Information PDF ");
		document.addSubject("Result For Criteria");
		document.addAuthor("UserName");
		document.addCreator("Author");
		
		
			}

	private void createTable(Section subCatPart,List<Author> result) {
		// TODO Auto-generated method stub
		 PdfPTable table = new PdfPTable(4);

         // t.setBorderColor(BaseColor.GRAY);
         // t.setPadding(4);
         // t.setSpacing(4);
         // t.setBorderWidth(1);

         PdfPCell c1 = new PdfPCell(new Phrase("Serial No."));
         c1.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(c1);

         c1 = new PdfPCell(new Phrase("Author Name"));
         c1.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(c1);

         c1 = new PdfPCell(new Phrase("University"));
         c1.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(c1);
         
         c1 = new PdfPCell(new Phrase("Homepage URL"));
         c1.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(c1);
         
         table.setHeaderRows(1);
         int i=0;
         for (Author author:result){
         table.addCell(Integer.toString(i));
         table.addCell(author.getName());
         table.addCell(author.getAffiliation());
         table.addCell(author.getUrl());
         i=i+1; 
         }
         subCatPart.add(table);
	}


	private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
                paragraph.add(new Paragraph(" "));
        }
}
	
}
