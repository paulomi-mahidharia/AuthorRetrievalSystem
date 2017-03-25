package application;
	
import com.neu.msd.AuthorRetriever.service.UserService;
import com.neu.msd.AuthorRetriever.service.UserServiceImpl;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			Scene scene = new Scene(grid,1000,1000, Color.BEIGE);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Text scenetitle = new Text("Welcome!");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 0, 0, 2, 1);

			Label userName = new Label("User Name:");
			grid.add(userName, 0, 1);

			TextField userTextField = new TextField();
			grid.add(userTextField, 1, 1);

			Label pw = new Label("Password:");
			grid.add(pw, 0, 2);

			PasswordField pwBox = new PasswordField();
			grid.add(pwBox, 1, 2);
			
			Button btn = new Button("Sign in");
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_CENTER);
			hbBtn.getChildren().add(btn);
			grid.add(hbBtn, 1, 4);
			
			final Text actiontarget = new Text();
	        grid.add(actiontarget, 1, 6);
	        
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent e) {
	                
	            	String username = userTextField.getText();
	            	String password = pwBox.getText();
	            	
	            	UserService user = new UserServiceImpl();
	            	Boolean isLoginSuccessful = user.login(username, password);
	            	System.out.println(isLoginSuccessful);
	            	
	            	if(isLoginSuccessful){
	            		GridPane grid2 = new GridPane();
	            		grid2.setAlignment(Pos.TOP_LEFT);
	            		grid2.setHgap(10);
	            		grid2.setVgap(10);
	            		grid2.setPadding(new Insets(25, 25, 25, 25));
	        			
	            		Scene scene2 = new Scene(grid2,1000,1000, Color.BEIGE);
	            		
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
	        			grid2.add(numberOfPapersField, 1, 3);
	        			
	        			Label nameOfConference = new Label("Conference published/not published for:");
	        			grid2.add(nameOfConference, 0, 4);
	        			
	        			ObservableList<String> options = 
	        				    FXCollections.observableArrayList(
	        				        "Published",
	        				        "Not published"
	        				    );
        				ComboBox comboBox = new ComboBox(options);
        				comboBox.setValue("Published");
        				grid2.add(comboBox, 1, 4);
        				
        				Label yearRange = new Label("Year range:");
	        			grid2.add(yearRange, 0, 5);
	        			
	        			ObservableList<String> options1 = 
	        				    FXCollections.observableArrayList(
	        				        "between",
	        				        "before",
	        				        "after"
	        				    );
        				ComboBox comboBox1 = new ComboBox(options1);
        				comboBox1.setValue("before");
        				grid2.add(comboBox1, 1, 5);
        				
        				final TextField fromYear = new TextField();
        		        final Label betweenYearLabel = new Label("to"); 
        		        final TextField toYear = new TextField();
        		        grid2.add(fromYear, 2, 5);
        		        System.out.println(comboBox1.getValue());
        		        
        		        comboBox1.setOnAction(new EventHandler<ActionEvent>() {
        		        	 
        		            @Override
        		            public void handle(ActionEvent e) {
        		            	String comboSelection = comboBox1.getSelectionModel().getSelectedItem().toString();
            		            
            		            if(comboBox1.getValue().equals("between")){
            		            	if(!grid2.getChildren().contains(betweenYearLabel)){
            		            		grid2.add(betweenYearLabel, 3, 5);
                		        	}
            		            	if(!grid2.getChildren().contains(toYear)){
            		            		grid2.add(toYear, 4, 5);
            		            	}
                		        }else if(comboBox1.getValue().equals("before")){
                		        	if(grid2.getChildren().contains(betweenYearLabel)){
                		        		grid2.getChildren().remove(betweenYearLabel);
                		        	}
                		        	if(grid2.getChildren().contains(toYear)){
                		        		grid2.getChildren().remove(toYear);
                		        	}
                		        	if(!grid2.getChildren().contains(fromYear)){
                    		        	grid2.add(fromYear, 2, 5);
                		        	}
                		        	
                		        }else if(comboBox1.getValue().equals("after")){
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
        		            
        		      
	            		
	        			primaryStage.setScene(scene2);
	        			primaryStage.show();
	            		
	            	}else{
	            		actiontarget.setFill(Color.FIREBRICK);
		                actiontarget.setText("Invalid credentials!");
	            	}
	            }
	        });
		      
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public GridPane getGridPane(){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		return grid;
	}
}
