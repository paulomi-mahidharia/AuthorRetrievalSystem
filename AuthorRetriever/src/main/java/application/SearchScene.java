package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SearchScene {
	
	public static Scene getSearchScene(){
		GridPane grid2 = new GridPane();
		grid2.setAlignment(Pos.TOP_LEFT);
		grid2.setHgap(10);
		grid2.setVgap(10);
		grid2.setPadding(new Insets(25, 25, 25, 25));
		
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
            }
		});
		
	    Scene scene2 = new Scene(grid2, 1000, 1000, Color.BEIGE);
	    return scene2;
	}
}
