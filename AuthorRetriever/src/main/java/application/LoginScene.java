package application;

import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.service.ConferenceService;
import com.neu.msd.AuthorRetriever.service.ConferenceServiceImpl;
import com.neu.msd.AuthorRetriever.service.UserService;
import com.neu.msd.AuthorRetriever.service.UserServiceImpl;
import com.neu.msd.AuthorRetriever.util.AlertUtil;
import com.neu.msd.AuthorRetriever.util.ConferenceUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;


@SuppressWarnings({"restriction"})
public class LoginScene {
	
	public static void displayLoginScene(Stage primaryStage){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
				
		Text scenetitle = new Text("Welcome!");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		userTextField.setPromptText("Enter username");
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		pwBox.setPromptText("Enter password");
		grid.add(pwBox, 1, 2);
		
		Button btn = new Button("Sign in");
		Button btnRegister = new Button("Register");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().addAll(btn,btnRegister);
		grid.add(hbBtn, 1, 4);
		
		final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
       
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	System.out.println("Yo!!!");
                RegistrationScene.getRegisterScene(primaryStage) ;		
            	
            }
        });
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                
            	String username = userTextField.getText();
            	String password = pwBox.getText();
            	
            	UserService user = new UserServiceImpl();
            	Boolean isLoginSuccessful = user.login(username, password);
            	System.out.println(isLoginSuccessful);
            	if(isLoginSuccessful){
            		List<String> conferences = new ArrayList<>();
            		ConferenceService conferenceService = new ConferenceServiceImpl();
            		try {
						conferenceService.retrieveAllConferences().forEach((conference) -> {
							conferences.add(conference.getName().toUpperCase());
						});
						ConferenceUtil.setConferences(conferences);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						AlertUtil.displayAlert("Error", 
								"Oops, you got soemthing wrong!",
								"No conferences available");
					}
    		        SearchScene.displaySearchScene(primaryStage); 		
            	}else{
            		actiontarget.setFill(Color.FIREBRICK);
	                actiontarget.setText("Invalid credentials!");
            	}
            }
        });
        
        Scene scene = new Scene(grid, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
