package com.neu.msd.DBLPXMLParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import com.neu.msd.DBLPXMLParser.handler.HandleArticle;
import com.neu.msd.DBLPXMLParser.handler.HandlePaper;
import com.neu.msd.DBLPXMLParser.model.Article;
import com.neu.msd.DBLPXMLParser.model.Paper;


public class Parser {


	@SuppressWarnings("restriction")
	public static void main(String[] args) throws JAXBException, FileNotFoundException, XMLStreamException, SQLException {
		
		//stax
		XMLInputFactory xif = XMLInputFactory.newFactory();
		FileInputStream in = new FileInputStream("dblp.xml");
		XMLEventReader xer = xif.createXMLEventReader(in);
		
		xer.next();

		JAXBContext jaxbContextPaper = JAXBContext.newInstance(Paper.class);
		Unmarshaller jaxbUnmarshallerPaper = jaxbContextPaper.createUnmarshaller();
		
		JAXBContext jaxbContextArticle = JAXBContext.newInstance(Article.class);
		Unmarshaller jaxbUnmarshallerArticle = jaxbContextArticle.createUnmarshaller();
		
		List<Paper> paperList = new ArrayList<Paper>();
		List<Article> articleList = new ArrayList<Article>();
		
		HandlePaper handlePaper = new HandlePaper();
		HandleArticle handleArticle = new HandleArticle();
		
		int counterPaper = 0;
		int counterArticle = 0;
		
		while(xer.hasNext()){
			if(xer.peek().isStartElement() 
					&& xer.peek().asStartElement().getName().getLocalPart().equals("inproceedings")){
				Paper paper = (Paper) jaxbUnmarshallerPaper.unmarshal(xer);
				paperList.add(paper);
				
				if(paperList.size() == 500){
					handlePaper.insertPaperRecords(paperList);
					//empty the list
					paperList = new ArrayList<Paper>();
				}
				counterPaper++;
			}else if(xer.peek().isStartElement() 
					&& xer.peek().asStartElement().getName().getLocalPart().equals("article")){
				
				Article article = (Article) jaxbUnmarshallerArticle.unmarshal(xer);
				articleList.add(article);
				if(articleList.size() == 500){
					handleArticle.insertArticleRecords(articleList);
					//empty the list
					articleList = new ArrayList<Article>();
				}
				counterArticle++;
			}else{
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
		
		System.out.println("No. of Papers Processed:"+counterPaper);
		System.out.println("No. of Articles Processed:"+counterPaper);
		xer.close();
		
	}

}
