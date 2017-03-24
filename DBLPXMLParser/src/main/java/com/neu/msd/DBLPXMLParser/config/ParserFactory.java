package com.neu.msd.DBLPXMLParser.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ParserFactory {
	
	public static <T> Unmarshaller getInstance(Class<T> modelClass) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(modelClass);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		return jaxbUnmarshaller;
	}
}
