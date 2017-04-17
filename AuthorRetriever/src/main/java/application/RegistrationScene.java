package application;

import com.neu.msd.AuthorRetriever.model.User;
import com.neu.msd.AuthorRetriever.service.UserService;
import com.neu.msd.AuthorRetriever.service.UserServiceImpl;

import ch.qos.logback.core.boolex.Matcher;
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

import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.*;

import java.util.regex.*;

import org.apache.commons.lang.Validate;

@SuppressWarnings({"restriction"})
public class RegistrationScene {
	
	

	public static void  getRegisterScene(Stage primaryStage){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
				
		Text scenetitle = new Text("Registration Page!");
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
		
		Label reEnterpw = new Label("Re-Enter Password:");
		grid.add(reEnterpw, 0, 3);

		PasswordField renterpwBox = new PasswordField();
		renterpwBox.setPromptText("Re-Enter password");
		grid.add(renterpwBox, 1, 3);

		Button btn = new Button("Register");
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
            	String renterpassword=renterpwBox.getText();
            	UserService user = new UserServiceImpl();
            	
            	
            	if(validate(password)){
            	System.out.println("To!!!");
            	if (password.equals(renterpassword)){
            		User userObject= new User(username,password);
            		Boolean isLoginSuccessful = user.registerUser(userObject);
            		if(isLoginSuccessful){
            			SearchScene.displaySearchScene(primaryStage); 		
            		}else{
            			actiontarget.setFill(Color.FIREBRICK);
            			actiontarget.setText("Invalid credentials!");
            		}
            	}
            	else{
            		actiontarget.setFill(Color.FIREBRICK);
        			actiontarget.setText("Password and Renter Password  do not match!");
            	}
            }else{
            	actiontarget.setFill(Color.FIREBRICK);
    			actiontarget.setText("Password doesn't match Password Criteria");
        	}
            }
            	
            });
        
        Scene registerScene = new Scene(grid, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		primaryStage.setScene(registerScene);
		primaryStage.show();
		
	}
	 public static boolean validate(final String password){
		  Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		  java.util.regex.Matcher matcher = pattern.matcher(password);
		  return matcher.matches();

	  }
	
}
