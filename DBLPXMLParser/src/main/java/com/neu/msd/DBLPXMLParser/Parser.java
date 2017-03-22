package com.neu.msd.DBLPXMLParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import com.neu.msd.DBLPXMLParser.config.ParserFactory;
import com.neu.msd.DBLPXMLParser.handler.HandleArticle;
import com.neu.msd.DBLPXMLParser.handler.HandlePaper;
import com.neu.msd.DBLPXMLParser.handler.HandleProceeding;
import com.neu.msd.DBLPXMLParser.model.Article;
import com.neu.msd.DBLPXMLParser.model.Paper;
import com.neu.msd.DBLPXMLParser.model.Proceeding;


public class Parser {

	public static void main(String[] args) throws JAXBException, FileNotFoundException, XMLStreamException, SQLException {
		
		//stax
		XMLInputFactory xif = XMLInputFactory.newFactory();
		FileInputStream in = new FileInputStream("dblp.xml");
		XMLEventReader xer = xif.createXMLEventReader(in);
		
		xer.next();
		
		/*JAXBContext jaxbContextPaper = JAXBContext.newInstance(Paper.class);
		Unmarshaller jaxbUnmarshallerPaper = jaxbContextPaper.createUnmarshaller();
		
		JAXBContext jaxbContextArticle = JAXBContext.newInstance(Article.class);
		Unmarshaller jaxbUnmarshallerArticle = jaxbContextArticle.createUnmarshaller();*/
		
		Unmarshaller jaxbUnmarshallerPaper = ParserFactory.getInstance(Paper.class);
		Unmarshaller jaxbUnmarshallerArticle = ParserFactory.getInstance(Article.class);
		Unmarshaller jaxbUnmarshallerProceeding = ParserFactory.getInstance(Proceeding.class);
		
		List<Paper> paperList = new ArrayList<Paper>();
		List<Article> articleList = new ArrayList<Article>();
		List<Proceeding> proceedingList = new ArrayList<Proceeding>();
		
		HandlePaper handlePaper = new HandlePaper();
		HandleArticle handleArticle = new HandleArticle();
		HandleProceeding handleProceeding = new HandleProceeding();
		
		int counterPaper = 0;
		int counterArticle = 0;
		int counterProceeding = 0;
		
		while(xer.hasNext()){
			/*if(xer.peek().isStartElement() 
					&& xer.peek().asStartElement().getName().getLocalPart().equals("inproceedings")){
				Paper paper = (Paper) jaxbUnmarshallerPaper.unmarshal(xer);
				paperList.add(paper);
				
				if(paperList.size() == 500){
					handlePaper.insertPaperRecords(paperList);
					//empty the list
					paperList = new ArrayList<Paper>();
				}
				counterPaper++;
			}else*/ if(xer.peek().isStartElement() 
					&& xer.peek().asStartElement().getName().getLocalPart().equals("proceedings")){
				
				Proceeding proceeding = (Proceeding) jaxbUnmarshallerProceeding.unmarshal(xer);
				proceedingList.add(proceeding);
				if(proceedingList.size() == 500){
					handleProceeding.insertProceedingRecords(proceedingList);
					//empty the list
					proceedingList = new ArrayList<Proceeding>();
				}
				counterProceeding++;
			}/*else if(xer.peek().isStartElement() 
					&& xer.peek().asStartElement().getName().getLocalPart().equals("article")){
				
				Article article = (Article) jaxbUnmarshallerArticle.unmarshal(xer);
				articleList.add(article);
				if(articleList.size() == 500){
					handleArticle.insertArticleRecords(articleList);
					//empty the list
					articleList = new ArrayList<Article>();
				}
				counterArticle++;
			}*/else{
				// any other information if you need to parse
			}
			xer.nextEvent();
		}
		
		// handle unprocessed elements in the paerList
		if(!paperList.isEmpty()){
			handlePaper.insertPaperRecords(paperList);
			paperList = new ArrayList<Paper>();
		}
		
		// handle unprocessed elements in the articleList
		if(!articleList.isEmpty()){
			handleArticle.insertArticleRecords(articleList);
			articleList = new ArrayList<Article>();
		}
		
		// handle unprocessed elements in the proceedingList
		if(!proceedingList.isEmpty()){
			handleProceeding.insertProceedingRecords(proceedingList);
			proceedingList = new ArrayList<Proceeding>();
		}
		
		System.out.println("No. of Papers Processed:"+counterPaper);
		System.out.println("No. of Articles Processed:"+counterArticle);
		System.out.println("No. of Proceeding Processed:"+counterProceeding);
		xer.close();
		
	}

}
