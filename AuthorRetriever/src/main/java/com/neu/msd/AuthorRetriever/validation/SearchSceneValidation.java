package com.neu.msd.AuthorRetriever.validation;

import com.neu.msd.AuthorRetriever.constants.ValidationConstants;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

@SuppressWarnings({ "rawtypes", "restriction" })
public class SearchSceneValidation {
	
	public static String validateCriteria(CheckBox paperCheck, CheckBox serviceCheck){
		if(paperCheck.isSelected() || serviceCheck.isSelected())
			return ValidationConstants.VALID_CRITERIA;
		else
			return ValidationConstants.NO_CRITERIA_SELECTED;
	}
	
	public static String validatePaperInfo(TextField numberOfPapersField,
											TextField confName,
											ComboBox yearRangeComboBox,
											TextField fromYear,
											TextField toYear,
											TextField keyword){
    		
		int numberOfPublications = 0;
		
		//Check minimum number of papers
		if(!numberOfPapersField.getText().isEmpty()){
			try{
				numberOfPublications = Integer.parseInt(numberOfPapersField.getText());
			}catch(NumberFormatException ex){
				//error
				return ValidationConstants.INVALID_NUMBER_OF_PAPERS;
			}
    		
		}
		
		// Validate date if everything else is empty
		if(numberOfPublications == 0 && keyword.getText().isEmpty() && confName.getText().isEmpty()){
			
				//At least date must be present 
				String isDateValid = isDateValid(yearRangeComboBox.getValue().toString(), 
												fromYear.getText(), 
												toYear.getText());
				
				if(!isDateValid.equalsIgnoreCase(ValidationConstants.VALID_DATE)){
					return ValidationConstants.INVALID_DATE;
				}
		}
		
		// Validate range for number of publications
		if(numberOfPublications != 0){
			if(!isNumberOfPapersValid(numberOfPublications)){
				return ValidationConstants.INVALID_NUMBER_OF_PAPERS;
			}
		}
		
		// Validate conference name
		if(!confName.getText().isEmpty()){
			if(!isStringValid(confName.getText())){
				return ValidationConstants.INVALID_CONFERENCE_NAME;
			}
		}
		
		// Validate Keyword name
		if(!keyword.getText().isEmpty()){
			if(!isStringValid(keyword.getText())){
				return ValidationConstants.INVALID_KEYWORD;
			}
		}
		
		//Validate date
		if(!fromYear.getText().isEmpty() || !toYear.getText().isEmpty()){
			String isDateValid = isDateValid(yearRangeComboBox.getValue().toString(), 
											fromYear.getText(), 
											toYear.getText());
			if(!isDateValid.equalsIgnoreCase(ValidationConstants.VALID_DATE)){
				return ValidationConstants.INVALID_DATE;
			}
		}
		
		return ValidationConstants.VALID_PAPER_CRITERIA;
	}
	
	public static String validateServiceInformation(TextField confNameServedIn, 
													ComboBox yearRangeServedComboBox, 
													TextField fromYearServed, 
													TextField toYearServed) {
		
		// Validate conference name
		if(!confNameServedIn.getText().isEmpty()){
			if(!isStringValid(confNameServedIn.getText())){
				return ValidationConstants.INVALID_CONFERENCE_NAME;
			}
		}
		
		//Validate date
		if(!fromYearServed.getText().isEmpty() || !toYearServed.getText().isEmpty()){
			String isDateValid = isDateValid(yearRangeServedComboBox.getValue().toString(), 
											fromYearServed.getText(), 
											toYearServed.getText());
			if(!isDateValid.equalsIgnoreCase(ValidationConstants.VALID_DATE)){
				return ValidationConstants.INVALID_DATE;
			}
		}
		
		return ValidationConstants.VALID_SERVICE_CRITERIA;
	}
	
	public static String isDateValid(String dateOption, String start, String end){
		
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
				System.out.println(startDate);
				System.out.println(endDate);
				System.out.println(isYearInValid(startDate));
				System.out.println(isYearInValid(endDate));
				if(startDate == 0 || endDate == 0 || isYearInValid(startDate) || isYearInValid(endDate))
					return ValidationConstants.INVALID_DATE_RANGE;
				else 
					return ValidationConstants.VALID_DATE;
			case "before":
				if(startDate == 0 || isYearInValid(startDate))
					return ValidationConstants.INVALID_DATE;
				else 
					return ValidationConstants.VALID_DATE;
			case "after":
				if(startDate == 0 || isYearInValid(startDate))
					return ValidationConstants.INVALID_DATE;
				else 
					return ValidationConstants.VALID_DATE;
			default:
				return ValidationConstants.VALID_DATE;
		}
	}
	
	public static boolean isYearInValid(int year){
		if((year+"").length() != 4)
			return true;
		
		if(year < 1600 || year > 2020)
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
