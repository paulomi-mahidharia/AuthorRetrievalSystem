package com.neu.msd.AuthorRetriever.validation;

import com.neu.msd.AuthorRetriever.constants.ValidationConstants;
import com.neu.msd.AuthorRetriever.model.Paper;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;
import com.neu.msd.AuthorRetriever.model.ServiceInfo;

public class SearchSceneValidation {
	
	public static String validateSearchCriteria(SearchCriteria criteria){
		
		String validationStatus = "";
		
		Paper paperInfo = criteria.getPaperInfo();
		ServiceInfo serviceInfo = criteria.getServiceInfo();
		
		if(paperInfo == null && serviceInfo == null){
			return ValidationConstants.NO_CRITERIA_SELECTED;
		}
		
		if(paperInfo != null){
			
			// Validate date if everything else is empty
			if(paperInfo.getNumOfPapersPublished() == 0 &&
				paperInfo.getKeyword().isEmpty() &&
				paperInfo.getConferenceName().isEmpty()){
				
					//At least date must be present 
					String isDateValid = isDateValid(paperInfo.getOptions(), 
														paperInfo.getStartDate()+"", 
														paperInfo.getEndDate()+"");
					System.out.println("isDateValid:::"+isDateValid);
					if(isDateValid.equalsIgnoreCase(ValidationConstants.VALID_DATE)){
						return ValidationConstants.VALID_CRITERIA;
					}else{
						return ValidationConstants.INVALID_PAPER_CRITERIA;
					}
			}else{
				// Validate number of papers published
				if(paperInfo.getNumOfPapersPublished() != 0){
					if(!isNumberOfPapersValid(paperInfo.getNumOfPapersPublished())){
						return ValidationConstants.INVALID_NUMBER_OF_PAPERS;
					}
				}
				
				// Validate conference name
				if(!paperInfo.getConferenceName().isEmpty()){
					if(!isStringValid(paperInfo.getConferenceName())){
						return ValidationConstants.INVALID_CONFERENCE_NAME;
					}
				}
				
				// Validate Keyword name
				if(!paperInfo.getKeyword().isEmpty()){
					if(!isStringValid(paperInfo.getKeyword())){
						return ValidationConstants.INVALID_KEYWORD;
					}
				}
				
				//Validate date
				if(paperInfo.getStartDate() != 0 || paperInfo.getEndDate() != 0){
					String isDateValid = isDateValid(paperInfo.getOptions(), 
							paperInfo.getStartDate()+"", 
							paperInfo.getEndDate()+"");
					if(!isDateValid.equalsIgnoreCase(ValidationConstants.VALID_DATE)){
						return ValidationConstants.INVALID_DATE;
					}
				}
				
				return ValidationConstants.VALID_CRITERIA;
			}
		}
		
		if(serviceInfo != null){
			
		}
		
		return validationStatus;
	}
	
	public static String isDateValid(String dateOption, String start, String end){
		
		System.out.println("dateOption:::"+dateOption);
		int startDate;
		int endDate;
		try{
			startDate = Integer.parseInt(start);
			endDate = Integer.parseInt(end);
		}catch(NumberFormatException e){
			return ValidationConstants.INVALID_DATE;
		}
		
		switch(dateOption){
			case "between":
				if(startDate == 0 || endDate == 0 || isYearInValid(startDate) || isYearInValid(endDate)){
					return ValidationConstants.INVALID_DATE_RANGE;
				}
			case "before":
				if(startDate == 0 || isYearInValid(startDate)){
					return ValidationConstants.INVALID_DATE;
				}
			case "after":
				if(startDate == 0 || isYearInValid(startDate)){
					return ValidationConstants.INVALID_DATE;
				}
			default:
				return ValidationConstants.VALID_DATE;
		}
	}
	
	public static boolean isYearInValid(int year){
		if((year+"").length() != 4)
			return false;
		
		if(year < 1960 || year > 2020)
			return true;
		
		return false;
	}
	
	public static boolean isNumberOfPapersValid(int numberOfPapers){
		return Integer.toString(numberOfPapers).matches("^([1-9][0-9]{0,2})$");
	}
	
	public static boolean isStringValid(String str){
		return str.matches("^[a-zA-Z]+[a-zA-Z0-9]*$");
	}
}
