package application;
	
import com.neu.msd.AuthorRetriever.service.UserService;
import com.neu.msd.AuthorRetriever.service.UserServiceImpl;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			Scene scene = new Scene(grid,400,400, Color.BEIGE);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
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
	            		grid2.setAlignment(Pos.CENTER);
	            		grid2.setHgap(10);
	            		grid2.setVgap(10);
	            		grid2.setPadding(new Insets(25, 25, 25, 25));
	        			
	            		Scene scene2 = new Scene(grid2,400,400, Color.BEIGE);
	            		
	            		Text scenetitle = new Text("Welcome to Search page!");
	            		grid2.add(scenetitle, 0, 0, 2, 1);
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
}
