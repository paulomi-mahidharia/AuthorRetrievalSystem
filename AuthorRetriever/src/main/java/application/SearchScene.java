package application;

import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.AND_RADIO;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.BUTTON_STYLE;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.OR_RADIO;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.SEARCH_AUTHORS;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.AUTHOR_NAME;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.CONFERENCE_PUBLISHED;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.CONFERENCE_SERVED;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.FONT_TYPE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.HEADER_FONT_SIZE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.KEYWORD_LABEL;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.NUM_OF_PUBLICATIONS;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.POSITION_OPTIONS;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.POSITION_SERVED;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.PROMPT_AUTHO_NAME;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.PROMPT_FROM_YEAR;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.PROMPT_KEYWORD;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.PROMPT_NUM_OF_PUBLICATIONS;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.PROMPT_TO_YEAR;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.PUBLISH_OPTIONS;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_GRID_GAP;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_GRID_PADDING;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH_AUTHOR_INFO;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH_PAPER_INFO;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH_SERVICE_INFO;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEPARATOR_STYLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SERVED_OPTIONS;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.TO_LABEL;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.YEAR_RANGE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.YEAR_RANGE_OPTIONS;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.ALERT_ERROR;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.ALERT_HEADER;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.NO_AUTHORS_FOUND;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.NO_CRITERIA_SELECTED;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.SQL_FAILURE;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.VALID_AUHTOR;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.VALID_PAPER_CRITERIA;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.VALID_SERVICE_CRITERIA;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.CheckComboBox;

import com.neu.msd.AuthorRetriever.constants.PositionAlias;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.Paper;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;
import com.neu.msd.AuthorRetriever.model.ServiceInfo;
import com.neu.msd.AuthorRetriever.service.ConferenceService;
import com.neu.msd.AuthorRetriever.service.ConferenceServiceImpl;
import com.neu.msd.AuthorRetriever.service.SearchService;
import com.neu.msd.AuthorRetriever.service.SearchServiceImpl;
import com.neu.msd.AuthorRetriever.util.AlertUtil;
import com.neu.msd.AuthorRetriever.util.ConferenceUtil;
import com.neu.msd.AuthorRetriever.util.NavigationBar;
import com.neu.msd.AuthorRetriever.util.SceneStack;
import com.neu.msd.AuthorRetriever.validation.SearchSceneValidation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class SearchScene {
	
	private static StackPane stackPane = null;
	private static List<Author> authors;
	private static GridPane grid = new GridPane();
		
	public static void displaySearchScene(Stage primaryStage){
		
		stackPane = new StackPane();
		//GridPane grid = new GridPane();
		SearchCriteria searchCriteria = new SearchCriteria();
		
		//Initiates the grid and configures it
		grid = setUpGrid(grid);
		
		//Sets up the navigation bar
		BorderPane titleHbox = NavigationBar.getHeaderPane(SEARCH, primaryStage);
		grid.add(titleHbox, 0, 0, 20, 1);
		
		//.............................................................................................
		
		/**
		 * SET PAPER INFORMATION CRITERIA SECTION
		 */
		
		//CheckBox to select/unselect paper information criteria
		CheckBox paperCheck = new CheckBox(SEARCH_PAPER_INFO);
		paperCheck.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, HEADER_FONT_SIZE));
		grid.add(paperCheck, 0, 2);
		
		//Row for number of publications
		Label numberOfPapers = new Label(NUM_OF_PUBLICATIONS);
		grid.add(numberOfPapers, 0, 3);
		
		TextField numberOfPapersField = new TextField();
		numberOfPapersField.setPromptText(PROMPT_NUM_OF_PUBLICATIONS);
		grid.add(numberOfPapersField, 1, 3);
		
		//Row for selecting conferences published/not published in
		Label nameOfConference = new Label(CONFERENCE_PUBLISHED);
		grid.add(nameOfConference, 0, 4);
		
		ObservableList<String> conferenceOptions = FXCollections.observableArrayList(PUBLISH_OPTIONS);

		ComboBox conferenceOptionsComboBox = new ComboBox(conferenceOptions);
		conferenceOptionsComboBox.setValue(conferenceOptions.get(0));
		grid.add(conferenceOptionsComboBox, 1, 4);
		
		final ObservableList<String> conferenceNamesList = FXCollections.observableArrayList();
		ConferenceUtil.getConferences().forEach((conference) -> {
			conferenceNamesList.add(conference);
		});

		final CheckComboBox<String> conferenceNamesComboBox = new CheckComboBox<String>(conferenceNamesList);
		grid.add(conferenceNamesComboBox, 2, 4);

		//Row for selecting date options
		Label yearRange = new Label(YEAR_RANGE);
		grid.add(yearRange, 0, 5);
		
		ObservableList<String> paperInfoYearOptions = FXCollections.observableArrayList(YEAR_RANGE_OPTIONS);
		ComboBox yearRangeComboBox = new ComboBox(paperInfoYearOptions);
		yearRangeComboBox.setValue(paperInfoYearOptions.get(0));
		grid.add(yearRangeComboBox, 1, 5);
		
		final TextField fromYear = new TextField();
		fromYear.setPromptText(PROMPT_FROM_YEAR);
		
	    final Label betweenYearLabel = new Label(TO_LABEL); 
	    
	    final TextField toYear = new TextField();
	    toYear.setPromptText(PROMPT_TO_YEAR);
	    toYear.setDisable(true);
	    
	    grid.add(fromYear, 2, 5);
	    grid.add(betweenYearLabel, 3, 5);
	    grid.add(toYear, 4, 5, 13, 1);
	    
	    yearRangeComboBox.setOnAction(new EventHandler<ActionEvent>() {
	    	 
	        @Override
	        public void handle(ActionEvent e) {
	        	String yearRangeSelection = yearRangeComboBox.getValue().toString();
	            
	        	if(yearRangeSelection.equals(paperInfoYearOptions.get(0)) || yearRangeSelection.equals(paperInfoYearOptions.get(2))){
	        		toYear.setDisable(true);
	        	}else{
	        		toYear.setDisable(false);
	        	}
	        }
	    });
	    
	    //Row for selecting title or keyword
	    Label titleKeyword = new Label(KEYWORD_LABEL);
		grid.add(titleKeyword, 0, 6);
		
		TextField titleKeywordValue = new TextField();
		titleKeywordValue.setPromptText(PROMPT_KEYWORD);
		grid.add(titleKeywordValue, 1, 6);
		
		//Row to add union condition
		final ToggleGroup unionGroup = new ToggleGroup();
		
		RadioButton radioButtonAnd = new RadioButton(AND_RADIO);
		radioButtonAnd.setToggleGroup(unionGroup);
		radioButtonAnd.setUserData(AND_RADIO);
		radioButtonAnd.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, HEADER_FONT_SIZE));
		
		RadioButton radioButtonOr = new RadioButton(OR_RADIO);
		radioButtonOr.setUserData(OR_RADIO);
		radioButtonOr.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, HEADER_FONT_SIZE));
		radioButtonOr.setToggleGroup(unionGroup);
		
		HBox hBoxAndOr = new HBox();
		hBoxAndOr.getChildren().addAll(radioButtonAnd, radioButtonOr);
		hBoxAndOr.setSpacing(30);
		hBoxAndOr.setAlignment(Pos.BOTTOM_CENTER);
		grid.add(hBoxAndOr, 0, 8, 20, 1);
		
		unionGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

				if (unionGroup.getSelectedToggle() != null) {
					if(unionGroup.getSelectedToggle().getUserData().toString().equals(radioButtonOr.getText())){
						searchCriteria.setUnion(true);
					}else{
						searchCriteria.setUnion(false);
					}
	            }     
			}
			
		});
		

		/**
		 * SET SERVICE INFORMATION CRITERIA SECTION
		 */
		
		//CheckBox to select/unselect service information criteria
		CheckBox serviceCheck = new CheckBox(SEARCH_SERVICE_INFO);
		serviceCheck.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, HEADER_FONT_SIZE));
		grid.add(serviceCheck, 0, 10);
		
		//Row for selecting conferences served/not served in
		Label servedInConference = new Label(CONFERENCE_SERVED);
		grid.add(servedInConference, 0, 11);
		
		ObservableList<String> serveOptions = FXCollections.observableArrayList(SERVED_OPTIONS);
		ComboBox serveComboBox = new ComboBox(serveOptions);
		serveComboBox.setValue(serveOptions.get(0));
		grid.add(serveComboBox, 1, 11);
		
		final CheckComboBox<String> checkConfServedBox = new CheckComboBox<String>(conferenceNamesList);
		grid.add(checkConfServedBox, 2, 11);
		
		//Row for selecting date options
		Label yearRangeServed = new Label(YEAR_RANGE);
		grid.add(yearRangeServed, 0, 12);
		
		ObservableList<String> servedYearOptions = FXCollections.observableArrayList(YEAR_RANGE_OPTIONS);
		ComboBox yearRangeServedComboBox = new ComboBox(servedYearOptions);
		yearRangeServedComboBox.setValue(servedYearOptions.get(0));
		grid.add(yearRangeServedComboBox, 1, 12);
		
		final TextField fromYearServed = new TextField();
		fromYearServed.setPromptText(PROMPT_FROM_YEAR);
		
		final Label betweenYearServedLabel = new Label(TO_LABEL); 
	    
		final TextField toYearServed = new TextField();
	    toYearServed.setPromptText(PROMPT_TO_YEAR);
	    toYearServed.setDisable(true);
	    
	    grid.add(fromYearServed, 2, 12);
	    grid.add(betweenYearServedLabel, 3, 12);
	    grid.add(toYearServed, 4, 12, 13, 1);
	    
	    yearRangeServedComboBox.setOnAction(new EventHandler<ActionEvent>() {
	    	 
	        @Override
	        public void handle(ActionEvent e) {
	        	String yearRangeServedSelection = yearRangeServedComboBox.getValue().toString();
	            
	            if(yearRangeServedSelection.equals(servedYearOptions.get(0)) || yearRangeServedSelection.equals(servedYearOptions.get(2))){
	            	toYearServed.setDisable(true);
	            }else{
	            	toYearServed.setDisable(false);
	            }
	        }
	    });
	    
	    //Row for selecting position served as
	    Label position = new Label(POSITION_SERVED);
		grid.add(position, 0, 13);
		
		ObservableList<String> positionOptions = FXCollections.observableArrayList(POSITION_OPTIONS);
		
		ComboBox positionComboBox = new ComboBox(positionOptions);
		positionComboBox.setValue(positionOptions.get(0));
		grid.add(positionComboBox, 1, 13);
		
		Separator separator = new Separator();
		separator.setOrientation(Orientation.HORIZONTAL);
		separator.setStyle(SEPARATOR_STYLE);
		grid.add(separator, 0, 15, 20, 1);
		
		/**
		 * SET AUTHOR INFORMATION CRITERIA SECTION
		 */
		
		//CheckBox to select/unselect aithor information criteria
		CheckBox authorCheck = new CheckBox(SEARCH_AUTHOR_INFO);
		authorCheck.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, HEADER_FONT_SIZE));
		grid.add(authorCheck, 0, 17);
		
		//Row for getting author name
		Label authorNameLabel = new Label(AUTHOR_NAME);
		grid.add(authorNameLabel, 0, 18);
		
		TextField authorNameValue = new TextField();
		authorNameValue.setPromptText(PROMPT_AUTHO_NAME);
		grid.add(authorNameValue, 1, 18);
		
		Button btnSearch = new Button(SEARCH_AUTHORS);
		btnSearch.setStyle(BUTTON_STYLE);
		
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(btnSearch);
		hbBtn.setSpacing(10);
		grid.add(hbBtn, 1, 25);
		
		stackPane.getChildren().add(grid);
		
		//Displays search scene
		Scene searchScene = new Scene(stackPane, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		SceneStack.setCurrentScene(searchScene);
		primaryStage.setScene(searchScene);
		primaryStage.show();
		
		//Click event on SEARCH button
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	Paper paperInfo = null;
            	ServiceInfo serviceInfo = null;
            	String authorName = null;
            	
            	String isCriteriaValid = SearchSceneValidation.validateCriteria(paperCheck, serviceCheck, authorCheck);
            	
            	
            	if(isCriteriaValid.equalsIgnoreCase(NO_CRITERIA_SELECTED)){
            		AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, NO_CRITERIA_SELECTED);
            		return;
            	}else{
            		if(paperCheck.isSelected()){
            			
            			String conferenceSelection = conferenceNamesComboBox.getCheckModel().getCheckedItems().toString();
            			
            			String isPaperInfoValid = SearchSceneValidation.validatePaperInfo(numberOfPapersField, 
            										conferenceSelection.substring(1, conferenceSelection.length() - 1).trim(),
													yearRangeComboBox,
													fromYear,
													toYear,
													titleKeywordValue);
            			
            			if(!isPaperInfoValid.equalsIgnoreCase(VALID_PAPER_CRITERIA)){
            				AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, isPaperInfoValid);
            				return;
            			}else{
            				//set paper info bean
            				paperInfo = setPaperInformation(numberOfPapersField, 
															conferenceOptionsComboBox,
															conferenceSelection.substring(1, conferenceSelection.length() - 1).trim(),
															yearRangeComboBox,
															fromYear,
															toYear,
															titleKeywordValue);
            			}
            				
            		}
            		
            		if(serviceCheck.isSelected()){
            			
            			String conferenceServedSelection = checkConfServedBox.getCheckModel().getCheckedItems().toString();

            			String isServiceInfoValid = SearchSceneValidation.validateServiceInformation(conferenceServedSelection.substring(1, conferenceServedSelection.length() - 1),
														yearRangeServedComboBox,
														fromYearServed,
														toYearServed);
            			
            			if(!isServiceInfoValid.equalsIgnoreCase(VALID_SERVICE_CRITERIA)){
            				AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, isServiceInfoValid);
            				return;
            			}else{
            				//set paper info bean
            				serviceInfo = setServiceInformation(serveComboBox,
            													conferenceServedSelection.substring(1, conferenceServedSelection.length() - 1),
        														positionComboBox,
																yearRangeServedComboBox,
																fromYearServed,
																toYearServed);
            			}
            		}
            		
            		if(authorCheck.isSelected()){
            			
            			String isAuthorValid = SearchSceneValidation.validateAuthorCriteria(authorNameValue);
            			if(isAuthorValid.equalsIgnoreCase(VALID_AUHTOR)){
            				authorName = authorNameValue.getText();
            			}else{
            				AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, isAuthorValid);
            				return;
            			}
            		}
            	}
            		
        		searchCriteria.setPaperInfo(paperInfo);
        		searchCriteria.setServiceInfo(serviceInfo);
        		searchCriteria.setAuthorName(authorName);
        		
        		
        		searchAuthors(searchCriteria, searchScene, primaryStage);
    			/*SearchService searchService = new SearchServiceImpl();
        		List<Author> authors = new ArrayList<Author>();
				try {
					authors = searchService.searchAuthorsByCriteria(searchCriteria);
					
					if(!authors.isEmpty() && authors.size() != 0){
						SceneStack.pushSceneToStack(searchScene);
						ResultScene.displayResultScene(authors,primaryStage);
						
					}else{
						AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, NO_AUTHORS_FOUND);
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, SQL_FAILURE);
					return;
				}
        	*/}
		});
		
		primaryStage.show();
	}
	
	private static void searchAuthors(SearchCriteria searchCriteria, Scene searchScene, Stage primaryStage){
    	
		ProgressIndicator indicator = new ProgressIndicator();
		indicator.setMinSize(150, 150);
		VBox updatePane = new VBox();
        updatePane.setPadding(new Insets(10));
        updatePane.getChildren().addAll(indicator);
        updatePane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(updatePane);
		

        Task longTask = new Task<Void>() {
        	
            @Override
            protected Void call() throws Exception {
            	SearchService searchService = new SearchServiceImpl();
        		authors = new ArrayList<Author>();
        		try {
        			authors = searchService.searchAuthorsByCriteria(searchCriteria);
        			
        			
        		} catch (SQLException e1) {
        			// TODO Auto-generated catch block
        			AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, SQL_FAILURE);
        			return null;
        		}
        		return null;
            }
        };
        
        longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
            	grid.setDisable(false);
            	updatePane.setVisible(false);
            	if(!authors.isEmpty() && authors.size() != 0){
    				SceneStack.pushSceneToStack(searchScene);
    				ResultScene.displayResultScene(authors, primaryStage);
    			}else{
    				AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, NO_AUTHORS_FOUND);
    				return;
    			}
            }
        });
		/*SearchService searchService = new SearchServiceImpl();
		List<Author> authors = new ArrayList<Author>();
		try {
			authors = searchService.searchAuthorsByCriteria(searchCriteria);
			
			if(!authors.isEmpty() && authors.size() != 0){
				SceneStack.pushSceneToStack(searchScene);
				ResultScene.displayResultScene(authors,primaryStage);
				
			}else{
				AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, NO_AUTHORS_FOUND);
				return;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, SQL_FAILURE);
			return;
		}*/
        
        indicator.progressProperty().bind(longTask.progressProperty());
        updatePane.setVisible(true);
	    grid.setDisable(true);
        
        new Thread(longTask).start();
	}
		
	private static GridPane setUpGrid(GridPane grid) {
			
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(SCENE_GRID_GAP);
		grid.setVgap(SCENE_GRID_GAP);
		grid.setPadding(new Insets(SCENE_GRID_PADDING, SCENE_GRID_PADDING, SCENE_GRID_PADDING, SCENE_GRID_PADDING));
		
	
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(50);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(25);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(25);
		ColumnConstraints col4 = new ColumnConstraints();
		col3.setPercentWidth(20);
		grid.getColumnConstraints().addAll(col1,col2,col3, col4);
		
		return grid;
	}

	public static Paper setPaperInformation(TextField numberOfPapersField,
											ComboBox publishComboBox,
											String confName,
											ComboBox yearRangeComboBox,
											TextField fromYear,
											TextField toYear,
											TextField titleKeywordValue){
    		
		Paper paperInfo = new Paper();
		
		//Set minimum number of papers
		if(!numberOfPapersField.getText().isEmpty()){
			paperInfo.setNumOfPapersPublished(Integer.parseInt(numberOfPapersField.getText()));
		}
		
		//Set published or not published
		if(publishComboBox.getValue().equals(PUBLISH_OPTIONS.get(0))){
			paperInfo.setPublished(true);
		}else{
			paperInfo.setPublished(false);
		}
		
		//Set conference name
		paperInfo.setConferenceName(confName);
		
		//Set date options
		paperInfo.setOptions(yearRangeComboBox.getValue().toString());
		
		//Set start date and/or end date
		if(!fromYear.getText().isEmpty() || !toYear.getText().isEmpty())
			if(yearRangeComboBox.getValue().equals(YEAR_RANGE_OPTIONS.get(1))){
				if(!fromYear.getText().isEmpty()) 
					paperInfo.setStartDate(Integer.parseInt(fromYear.getText()));
				if(!toYear.getText().isEmpty()) 
					paperInfo.setEndDate(Integer.parseInt(toYear.getText()));
			}else if(yearRangeComboBox.getValue().equals(YEAR_RANGE_OPTIONS.get(0)) 
					|| yearRangeComboBox.getValue().equals(YEAR_RANGE_OPTIONS.get(2))){
				if(!fromYear.getText().isEmpty()) {
					paperInfo.setStartDate(Integer.parseInt(fromYear.getText()));
					paperInfo.setEndDate(Integer.parseInt(fromYear.getText()));
				}
		}
		
		//Set keyword
		paperInfo.setKeyword(titleKeywordValue.getText());
		
		return paperInfo;
	}	
	
	public static ServiceInfo setServiceInformation(ComboBox serveComboBox,
													String confNameServedIn,
													ComboBox positionComboBox,
													ComboBox yearRangeServedComboBox,
													TextField fromYearServed,
													TextField toYearServed){
		
		ServiceInfo serviceInfo = new ServiceInfo();
		
		//Set has served of not
		if(serveComboBox.getValue().toString().equalsIgnoreCase(SERVED_OPTIONS.get(0))){
    		serviceInfo.setHasServed(true);
		}else{
			serviceInfo.setHasServed(false);
		}
		
		//Set conference name
		serviceInfo.setConferenceName(confNameServedIn);
		
		//set options
		serviceInfo.setOptions(yearRangeServedComboBox.getValue().toString());
		
		//Set position
		String position = positionComboBox.getValue().toString();
		switch(position){
		
			case "All": serviceInfo.setPosition(PositionAlias.ALL_ALIAS);
				break;
			case "General Chair": serviceInfo.setPosition(PositionAlias.GENERAL_CHAIR_ALIAS);
				break;
			case "Program Chair": serviceInfo.setPosition(PositionAlias.PROGRAM_CHAIR_ALIAS);
				break;
			case "Conference Chair": serviceInfo.setPosition(PositionAlias.CONFERENCE_CHAIR_ALIAS);
				break;
			case "External Review Committee": serviceInfo.setPosition(PositionAlias.EXTERNAL_REVIEW_ALIAS);
				break;
			default : serviceInfo.setPosition(PositionAlias.ALL_ALIAS);
		}
		
		//Set start date and/or end date
		if(!fromYearServed.getText().isEmpty() || !toYearServed.getText().isEmpty()){
			if(yearRangeServedComboBox.getValue().equals(YEAR_RANGE_OPTIONS.get(1))){
				serviceInfo.setStartDate(Integer.parseInt(fromYearServed.getText()));
				serviceInfo.setEndDate(Integer.parseInt(toYearServed.getText()));
			}else if(yearRangeServedComboBox.getValue().equals(YEAR_RANGE_OPTIONS.get(0)) 
					|| yearRangeServedComboBox.getValue().equals(YEAR_RANGE_OPTIONS.get(2))){
				if(!fromYearServed.getText().isEmpty()) {
					serviceInfo.setStartDate(Integer.parseInt(fromYearServed.getText()));
					serviceInfo.setEndDate(Integer.parseInt(fromYearServed.getText()));
				}
			}
		}
		
		return serviceInfo;
	}
}
