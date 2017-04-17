package com.neu.msd.DBLPXMLParser;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import com.neu.msd.DBLPXMLParser.config.ParserFactory;
import com.neu.msd.DBLPXMLParser.handler.HandleArticle;
import com.neu.msd.DBLPXMLParser.handler.HandleAuthorAlias;
import com.neu.msd.DBLPXMLParser.handler.HandlePaper;
import com.neu.msd.DBLPXMLParser.handler.HandleProceeding;
import com.neu.msd.DBLPXMLParser.handler.HandleThesis;
import com.neu.msd.DBLPXMLParser.model.Article;
import com.neu.msd.DBLPXMLParser.model.AuthorAlias;
import com.neu.msd.DBLPXMLParser.model.MasterThesis;
import com.neu.msd.DBLPXMLParser.model.Paper;
import com.neu.msd.DBLPXMLParser.model.PhdThesis;
import com.neu.msd.DBLPXMLParser.model.Proceeding;
import com.neu.msd.DBLPXMLParser.model.Thesis;

public class Parser extends ParserBase {

	private String filePath;

	private static String DEFAULT_PATH = "dblp.xml";

	public Parser(String path) {
		filePath = path;
	}

	@Override
	public void executeParser() {

		String file = (!filePath.isEmpty() && !filePath.equals(null)) ? filePath : DEFAULT_PATH;
		Long start = System.currentTimeMillis();
		try {
			// stax
			XMLInputFactory xif = XMLInputFactory.newFactory();
			FileInputStream in = new FileInputStream(file);
			XMLEventReader xer = xif.createXMLEventReader(in);

			xer.next();

			Unmarshaller jaxbUnmarshallerPaper = ParserFactory.getInstance(Paper.class);
			Unmarshaller jaxbUnmarshallerArticle = ParserFactory.getInstance(Article.class);
			Unmarshaller jaxbUnmarshallerProceeding = ParserFactory.getInstance(Proceeding.class);
			Unmarshaller jaxbUnmarshallerPhdThesis = ParserFactory.getInstance(PhdThesis.class);
			Unmarshaller jaxbUnmarshallerMasterThesis = ParserFactory.getInstance(MasterThesis.class);
			Unmarshaller jaxbUnmarshallerAuthorAlias = ParserFactory.getInstance(AuthorAlias.class);

			List<Paper> paperList = new ArrayList<Paper>();
			List<Article> articleList = new ArrayList<Article>();
			List<Proceeding> proceedingList = new ArrayList<Proceeding>();
			List<Thesis> thesisList = new ArrayList<Thesis>();
			List<AuthorAlias> aliasList = new ArrayList<AuthorAlias>();

			HandlePaper handlePaper = new HandlePaper();
			HandleArticle handleArticle = new HandleArticle();
			HandleProceeding handleProceeding = new HandleProceeding();
			HandleThesis handleThesis = new HandleThesis();
			HandleAuthorAlias handleAuthorAlias = new HandleAuthorAlias();

			int counterPaper = 0;
			int counterArticle = 0;
			int counterProceeding = 0;
			int counterThesis = 0;

			while (xer.hasNext()) {
				try {
					if (xer.peek().isStartElement()
							&& xer.peek().asStartElement().getName().getLocalPart().equals("inproceedings")) {
						Paper paper = (Paper) jaxbUnmarshallerPaper.unmarshal(xer);
						paperList.add(paper);

						if (paperList.size() == 500) {
							handlePaper.insertPaperRecords(paperList);
							// empty the list
							paperList = new ArrayList<Paper>();
						}
						counterPaper++;
					} else if (xer.peek().isStartElement()
							&& xer.peek().asStartElement().getName().getLocalPart().equals("proceedings")) {

						Proceeding proceeding = (Proceeding) jaxbUnmarshallerProceeding.unmarshal(xer);
						proceedingList.add(proceeding);
						if (proceedingList.size() == 500) {
							handleProceeding.insertProceedingRecords(proceedingList);
							// empty the list
							proceedingList = new ArrayList<Proceeding>();
						}
						counterProceeding++;
					} else if (xer.peek().isStartElement()
							&& xer.peek().asStartElement().getName().getLocalPart().equals("article")) {

						Article article = (Article) jaxbUnmarshallerArticle.unmarshal(xer);
						articleList.add(article);
						if (articleList.size() == 500) {
							handleArticle.insertArticleRecords(articleList);
							// empty the list
							articleList = new ArrayList<Article>();
						}
						counterArticle++;
					} else if (xer.peek().isStartElement()
							&& (xer.peek().asStartElement().getName().getLocalPart().equals("phdthesis")
									|| xer.peek().asStartElement().getName().getLocalPart().equals("mastersthesis"))) {

						Thesis thesis;
						if (xer.peek().asStartElement().getName().getLocalPart().equals("phdthesis")) {
							thesis = (PhdThesis) jaxbUnmarshallerPhdThesis.unmarshal(xer);
						} else {
							thesis = (MasterThesis) jaxbUnmarshallerMasterThesis.unmarshal(xer);
						}
						thesisList.add(thesis);
						if (thesisList.size() == 500) {
							handleThesis.insertThesisRecords(thesisList);
							// empty the list
							thesisList = new ArrayList<Thesis>();
						}
						counterThesis++;
					} else if (xer.peek().isStartElement()
							&& xer.peek().asStartElement().getName().getLocalPart().equals("www")) {

						AuthorAlias authorAlias = (AuthorAlias) jaxbUnmarshallerAuthorAlias.unmarshal(xer);
						aliasList.add(authorAlias);
						if (aliasList.size() == 500) {
							handleAuthorAlias.insertAuthorAlias(aliasList);
							// empty the list
							aliasList = new ArrayList<AuthorAlias>();
						}
					} else {
						// any other information if you need to parse
					}
				} catch (Exception e) {
					System.out.println("Unable to parse record.");
				}
				xer.nextEvent();
			}

			// handle unprocessed elements in the paerList
			if (!paperList.isEmpty()) {
				handlePaper.insertPaperRecords(paperList);
				paperList = new ArrayList<Paper>();
			}

			// handle unprocessed elements in the articleList
			if (!articleList.isEmpty()) {
				handleArticle.insertArticleRecords(articleList);
				articleList = new ArrayList<Article>();
			}

			// handle unprocessed elements in the proceedingList
			if (!proceedingList.isEmpty()) {
				handleProceeding.insertProceedingRecords(proceedingList);
				proceedingList = new ArrayList<Proceeding>();
			}

			// handle unprocessed elements in the thesisList
			if (!thesisList.isEmpty()) {
				handleThesis.insertThesisRecords(thesisList);
				thesisList = new ArrayList<Thesis>();
			}

			// handle unprocessed elements in the authorAlias
			if (!aliasList.isEmpty()) {
				handleAuthorAlias.insertAuthorAlias(aliasList);
				aliasList = new ArrayList<AuthorAlias>();
			}

			System.out.println("No. of Papers Processed:" + counterPaper);
			System.out.println("No. of Articles Processed:" + counterArticle);
			System.out.println("No. of Proceeding Processed:" + counterProceeding);
			System.out.println("No. of Thesis Processed:" + counterThesis);

			Long end = System.currentTimeMillis();
			System.out.println("Total Time: " + (end - start) / 1000 + " seconds");
			xer.close();
		} catch (Exception e) {
			System.out.println("Error processing dblp.xml dataset");
			e.printStackTrace();
		}

	}

}
