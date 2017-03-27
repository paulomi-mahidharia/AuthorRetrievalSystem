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
			if(paperInfo.getNumOfPapersPublished() == 0 &&
				paperInfo.getKeyword().isEmpty() &&
				paperInfo.getConferenceName().isEmpty()){
				
					//Atleast date must be present
					String isDateValid = isDateValid(paperInfo.getOptions(), 
														paperInfo.getStartDate(), 
														paperInfo.getEndDate());
					if(isDateValid.equalsIgnoreCase(ValidationConstants.VALID_DATE)){
						return ValidationConstants.VALID_CRITERIA;
					}else{
						return ValidationConstants.INVALID_PAPER_CRITERIA;
					}
			}else{
				if(paperInfo.getStartDate() != 0){
					
				}
			}
		}
		
		if(serviceInfo != null){
			
		}
		
		return validationStatus;
	}
	
	public static String isDateValid(String dateOption, int startDate, int endDate){
		
		String dateValidStatus = "";
		
		switch(dateOption){
			case "between":
				if(startDate == 0 || endDate == 0 || isYearInValid(startDate) || isYearInValid(endDate)){
					dateValidStatus = ValidationConstants.INVALID_DATE_RANGE;
				}
			case "before":
				if(startDate == 0 || isYearInValid(startDate)){
					dateValidStatus = ValidationConstants.INVALID_DATE;
				}
			case "after":
				if(startDate == 0 || isYearInValid(startDate)){
					dateValidStatus = ValidationConstants.INVALID_DATE;
				}
			default:
				dateValidStatus = ValidationConstants.VALID_DATE;
		}
		return dateValidStatus;
		
	}
	
	public static boolean isYearInValid(int year){
		return !Integer.toString(year).matches("/^\\d{4}$/");
	}
}
