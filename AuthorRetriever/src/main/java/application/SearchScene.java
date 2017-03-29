package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.constants.ValidationConstants;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.Paper;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;
import com.neu.msd.AuthorRetriever.model.ServiceInfo;
import com.neu.msd.AuthorRetriever.service.SearchService;
import com.neu.msd.AuthorRetriever.service.SearchServiceImpl;
import com.neu.msd.AuthorRetriever.validation.SearchSceneValidation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings({ "rawtypes", "restriction", "unchecked" })
public class SearchScene {
	
		public static Scene getSearchScene(Stage primaryStage){
		
		GridPane grid2 = new GridPane();
		grid2.setAlignment(Pos.TOP_LEFT);
		grid2.setHgap(10);
		grid2.setVgap(10);
		grid2.setPadding(new Insets(25, 25, 25, 25));
		
		SearchCriteria searchCriteria = new SearchCriteria();
		
		Text scenetitle = new Text("SEARCH AUTHORS");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		scenetitle.setFill(Color.FIREBRICK);
		grid2.add(scenetitle, 0, 0);
		
		CheckBox paperCheck = new CheckBox("Search based on paper information");
		paperCheck.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid2.add(paperCheck, 0, 2);
		
		Label numberOfPapers = new Label("Number of minimum Publications:");
		grid2.add(numberOfPapers, 0, 3);
		
		TextField numberOfPapersField = new TextField();
		numberOfPapersField.setPromptText("Number of papers/articles");
		grid2.add(numberOfPapersField, 1, 3);
		
		Label nameOfConference = new Label("Conference published/not published for:");
		grid2.add(nameOfConference, 0, 4);
		
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Published in",
			        "Not published in"
			    );
		ComboBox publishComboBox = new ComboBox(options);
		publishComboBox.setValue("Published in");
		grid2.add(publishComboBox, 1, 4);
		
		publishComboBox.setOnAction(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
	        	String comboSelection = publishComboBox.getValue().toString();
			}
		});
		
		TextField confName = new TextField();
		confName.setPromptText("Conference name");
		grid2.add(confName, 2, 4);
		
		Label yearRange = new Label("Year range:");
		grid2.add(yearRange, 0, 5);
		
		ObservableList<String> options1 = 
			    FXCollections.observableArrayList(
			        "between",
			        "before",
			        "after"
			    );
		ComboBox yearRangeComboBox = new ComboBox(options1);
		yearRangeComboBox.setValue("before");
		grid2.add(yearRangeComboBox, 1, 5);
		
		final TextField fromYear = new TextField();
		fromYear.setPromptText("Year");
	    final Label betweenYearLabel = new Label("to"); 
	    final TextField toYear = new TextField();
	    toYear.setPromptText("Year");
	    grid2.add(fromYear, 2, 5);
	    System.out.println(yearRangeComboBox.getValue());
	    
	    yearRangeComboBox.setOnAction(new EventHandler<ActionEvent>() {
	    	 
	        @Override
	        public void handle(ActionEvent e) {
	        	String yearRangeSelection = yearRangeComboBox.getValue().toString();
	            
	            if(yearRangeSelection.equals("between")){
	            	if(!grid2.getChildren().contains(betweenYearLabel)){
	            		grid2.add(betweenYearLabel, 3, 5);
		        	}
	            	if(!grid2.getChildren().contains(toYear)){
	            		grid2.add(toYear, 4, 5);
	            	}
		        }else if(yearRangeSelection.equals("before")){
		        	if(grid2.getChildren().contains(betweenYearLabel)){
		        		grid2.getChildren().remove(betweenYearLabel);
		        	}
		        	if(grid2.getChildren().contains(toYear)){
		        		grid2.getChildren().remove(toYear);
		        	}
		        	if(!grid2.getChildren().contains(fromYear)){
			        	grid2.add(fromYear, 2, 5);
		        	}
		        	
		        }else if(yearRangeSelection.equals("after")){
		        	if(grid2.getChildren().contains(betweenYearLabel)){
		        		grid2.getChildren().remove(betweenYearLabel);
		        	}
		        	if(grid2.getChildren().contains(toYear)){
		        		grid2.getChildren().remove(toYear);
		        	}
		        	if(!grid2.getChildren().contains(fromYear)){
			        	grid2.add(fromYear, 2, 5);
		        	}
		        }
	        }
	    });
	    
	    Label titleKeyword = new Label("Keyword or Title:");
		grid2.add(titleKeyword, 0, 6);
		
		TextField titleKeywordValue = new TextField();
		titleKeywordValue.setPromptText("Enter keyword or title");
		grid2.add(titleKeywordValue, 1, 6);
		
		//Add union condition
		final ToggleGroup unionGroup = new ToggleGroup();
		
		RadioButton radioButtonAnd = new RadioButton("AND");
		radioButtonAnd.setToggleGroup(unionGroup);
		radioButtonAnd.setUserData("AND");
		radioButtonAnd.setSelected(true);
		grid2.add(radioButtonAnd, 0, 7);
		
		RadioButton radioButtonOr = new RadioButton("OR");
		radioButtonOr.setUserData("OR");
		radioButtonOr.setToggleGroup(unionGroup);
		grid2.add(radioButtonOr, 1, 7);
		
		unionGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub

				if (unionGroup.getSelectedToggle() != null) {
					if(unionGroup.getSelectedToggle().getUserData().toString().equals(radioButtonAnd.getText())){
						searchCriteria.setUnion(true);
					}else{
						searchCriteria.setUnion(false);
					}
	            }     
			}
			
		});
		
		CheckBox serviceCheck = new CheckBox("Search based on service information");
		serviceCheck.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid2.add(serviceCheck, 0, 8);
		
		Label servedInConference = new Label("Conference served/not served in:");
		grid2.add(servedInConference, 0, 9);
		
		ObservableList<String> serveOptions = 
			    FXCollections.observableArrayList(
			        "Served in",
			        "Not served in"
			    );
		ComboBox serveComboBox = new ComboBox(serveOptions);
		serveComboBox.setValue("Served in");
		grid2.add(serveComboBox, 1, 9);
		
		serveComboBox.setOnAction(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
	        	String serveComboSelection = serveComboBox.getValue().toString();
			}
		});
		
		TextField confNameServedIn = new TextField();
		confNameServedIn.setPromptText("Conference name");
		grid2.add(confNameServedIn, 2, 9);
		
		Label yearRangeServed = new Label("Year range:");
		grid2.add(yearRangeServed, 0, 10);
		
		ObservableList<String> servedYearOptions = 
			    FXCollections.observableArrayList(
			        "between",
			        "before",
			        "after"
			    );
		ComboBox yearRangeServedComboBox = new ComboBox(servedYearOptions);
		yearRangeServedComboBox.setValue("before");
		grid2.add(yearRangeServedComboBox, 1, 10);
		
		final TextField fromYearServed = new TextField();
		fromYearServed.setPromptText("Year");
	    final Label betweenYearServedLabel = new Label("to"); 
	    final TextField toYearServed = new TextField();
	    toYearServed.setPromptText("Year");
	    grid2.add(fromYearServed, 2, 10);
	    System.out.println(yearRangeServedComboBox.getValue());
	    
	    yearRangeServedComboBox.setOnAction(new EventHandler<ActionEvent>() {
	    	 
	        @Override
	        public void handle(ActionEvent e) {
	        	String yearRangeServedSelection = yearRangeServedComboBox.getValue().toString();
	            
	            if(yearRangeServedSelection.equals("between")){
	            	if(!grid2.getChildren().contains(betweenYearServedLabel)){
	            		grid2.add(betweenYearServedLabel, 3, 10);
		        	}
	            	if(!grid2.getChildren().contains(toYearServed)){
	            		grid2.add(toYearServed, 4, 10);
	            	}
		        }else if(yearRangeServedSelection.equals("before")){
		        	if(grid2.getChildren().contains(betweenYearServedLabel)){
		        		grid2.getChildren().remove(betweenYearServedLabel);
		        	}
		        	if(grid2.getChildren().contains(toYearServed)){
		        		grid2.getChildren().remove(toYearServed);
		        	}
		        	if(!grid2.getChildren().contains(fromYearServed)){
			        	grid2.add(fromYearServed, 2, 10);
		        	}
		        	
		        }else if(yearRangeServedSelection.equals("after")){
		        	if(grid2.getChildren().contains(betweenYearServedLabel)){
		        		grid2.getChildren().remove(betweenYearServedLabel);
		        	}
		        	if(grid2.getChildren().contains(toYearServed)){
		        		grid2.getChildren().remove(toYearServed);
		        	}
		        	if(!grid2.getChildren().contains(fromYearServed)){
			        	grid2.add(fromYearServed, 2, 10);
		        	}
		        }
	        }
	    });
	    
	    Label position = new Label("Position served as:");
		grid2.add(position, 0, 11);
		
		ObservableList<String> positionOptions = 
			    FXCollections.observableArrayList(
			        "Program Chair",
			        "Conference Chair",
			        "External Review Committee"
			    );
		ComboBox positionComboBox = new ComboBox(positionOptions);
		positionComboBox.setValue("Program Chair");
		grid2.add(positionComboBox, 1, 11);
		
		Button btn = new Button("Search Authors");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(btn);
		grid2.add(hbBtn, 1, 12);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	System.out.println("CLicked");
            	
            	Paper paperInfo = null;
            	if(paperCheck.isSelected()){
            		
            		paperInfo = new Paper();
            		
            		//Set minimum number of papers
            		if(!numberOfPapersField.getText().isEmpty()){
                		paperInfo.setNumOfPapersPublished(Integer.parseInt(numberOfPapersField.getText()));
            		}
            		
            		//Set published or not published
            		if(publishComboBox.getValue().equals("Published in")){
						paperInfo.setPublished(true);
					}else{
						paperInfo.setPublished(false);
					}
            		
            		//Set conference name
            		paperInfo.setConferenceName(confName.getText());
            		
            		//Set date options
            		paperInfo.setOptions(yearRangeServedComboBox.getValue().toString());
            		
					//Set start date and/or end date
            		if(yearRangeComboBox.getValue().equals("between")){
            			if(!fromYear.getText().isEmpty()) 
            				paperInfo.setStartDate(Integer.parseInt(fromYear.getText()));
            			if(!toYear.getText().isEmpty()) 
            				paperInfo.setEndDate(Integer.parseInt(toYear.getText()));
            		}else if(yearRangeComboBox.getValue().equals("before") 
        					|| yearRangeComboBox.getValue().equals("after")){
            			if(!fromYear.getText().isEmpty()) 
            				paperInfo.setStartDate(Integer.parseInt(fromYear.getText()));
            		}
            		
            		//Set keyword
            		paperInfo.setKeyword(titleKeywordValue.getText());
        		}
            	
            	ServiceInfo serviceInfo = null;
            	if(serviceCheck.isSelected()){
            		
            		serviceInfo = new ServiceInfo();
            		//Set has served of not
            		if(serveComboBox.getValue().toString().equalsIgnoreCase("Served in")){
                		serviceInfo.setHasServed(true);
            		}else{
            			serviceInfo.setHasServed(false);
            		}
            		
            		//Set conference name
            		serviceInfo.setConferenceName(confNameServedIn.getText());
            		
            		//Set position
            		serviceInfo.setPosition(positionComboBox.getValue().toString());
            		
            		//Set start date and/or end date
            		if(yearRangeServedComboBox.getValue().equals("between")){
            			serviceInfo.setStartDate(Integer.parseInt(fromYearServed.getText()));
            			serviceInfo.setEndDate(Integer.parseInt(toYearServed.getText()));
            		}else if(yearRangeServedComboBox.getValue().equals("before") 
        					|| yearRangeServedComboBox.getValue().equals("after")){
            			serviceInfo.setStartDate(Integer.parseInt(fromYearServed.getText()));
            		}
            	}
            		
        		searchCriteria.setPaperInfo(paperInfo);
        		searchCriteria.setServiceInfo(serviceInfo);

        		String isValid = SearchSceneValidation.validateSearchCriteria(searchCriteria);
        		System.out.println(isValid);
        		
        		if(isValid.equalsIgnoreCase(ValidationConstants.VALID_CRITERIA)){
        			SearchService searchService = new SearchServiceImpl();
            		List<Author> authors = new ArrayList<Author>();
    				try {
    					authors = searchService.searchAuthorsByCriteria(searchCriteria);
    					Scene resultScene = ResultScene.getResultScene(authors);
    					primaryStage.setScene(resultScene);
    					primaryStage.show();
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					//e1.printStackTrace();
    				}
        		}else{
        			//Display Error Message
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error");
        			alert.setHeaderText("Oops, you got soemthing wrong!");
        			alert.setContentText(isValid);

        			alert.showAndWait();
        		}

        	}
		});
		
		
	    Scene scene2 = new Scene(grid2, 1000, 1000, Color.BEIGE);
	    return scene2;
	}
}
