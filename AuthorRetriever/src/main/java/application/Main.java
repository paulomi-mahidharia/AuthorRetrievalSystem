package application;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.SearchSimilarProfileServiceImpl;

import javafx.application.Application;
<<<<<<< Updated upstream
import javafx.scene.Scene;
=======
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
>>>>>>> Stashed changes
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
<<<<<<< Updated upstream
			Scene loginScene = LoginScene.getLoginScene(primaryStage);
			primaryStage.setScene(loginScene);
			primaryStage.show();
=======
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			Scene scene = new Scene(grid,1000,1000, Color.BEIGE);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
>>>>>>> Stashed changes
			
			Author author = new Author();
			author.setAuthorId(575214);
			
			SearchSimilarProfileServiceImpl sl = new SearchSimilarProfileServiceImpl();
			sl.searchSimilarAuthorProfiles(author);
			
			
<<<<<<< Updated upstream
=======
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
	        				        "Option 1",
	        				        "Option 2",
	        				        "Option 3"
	        				    );
        				ComboBox comboBox = new ComboBox(options);
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
        				grid2.add(comboBox1, 1, 5);
	            		
	            		
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
>>>>>>> Stashed changes
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
