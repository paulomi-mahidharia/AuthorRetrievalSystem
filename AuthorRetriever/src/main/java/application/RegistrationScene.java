package application;

import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.BUTTON_STYLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.LOGIN;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.INVALID_CREDENTIALS;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.INVALID_CRITERIA;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.PASSWORD_MISMATCH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_GRID_GAP;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_GRID_PADDING;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.PASSWORD_PATTERN;
import static com.neu.msd.AuthorRetriever.util.HandCursor.showHandCursor;
import static com.neu.msd.AuthorRetriever.util.NavigationBar.getHeaderPane;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.REGISTER;
import java.util.regex.Pattern;

import com.neu.msd.AuthorRetriever.model.User;
import com.neu.msd.AuthorRetriever.service.UserService;
import com.neu.msd.AuthorRetriever.service.UserServiceImpl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * The below class is use to register user to the system.This class is used to build a Register page using javaFx.
 * The Registration page takes username ,password,re enter password as input
 * @Given A Primary Stage as input 
 * @return:A registration page that is build using JavaFx
 * 
 */

@SuppressWarnings({"restriction"})
public class RegistrationScene {
	
	
	private static GridPane setUpGrid() {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(SCENE_GRID_GAP);
		grid.setVgap(SCENE_GRID_GAP);
		grid.setPadding(new Insets(SCENE_GRID_PADDING, SCENE_GRID_PADDING, SCENE_GRID_PADDING, SCENE_GRID_PADDING));
		return grid;
	}


	public static void  getRegisterScene(Stage primaryStage){
		
		GridPane grid = setUpGrid();
		BorderPane scenetitle = getHeaderPane(REGISTER, primaryStage);
		scenetitle.getChildren().remove(0);
		scenetitle.getChildren().remove(1);
		grid.add(scenetitle, 0, 0, 10, 1);

				
		
		Label userName = new Label("User Name:");
		grid.add(userName, 0, 25);

		TextField userTextField = new TextField();
		userTextField.setPromptText("Enter username");
		grid.add(userTextField, 1, 25);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 26);

		PasswordField pwBox = new PasswordField();
		pwBox.setPromptText("Enter password");
		grid.add(pwBox, 1, 26);
		
		Label reEnterpw = new Label("Re-Enter Password:");
		grid.add(reEnterpw, 0, 27);

		PasswordField renterpwBox = new PasswordField();
		renterpwBox.setPromptText("Re-Enter password");
		grid.add(renterpwBox, 1, 27);

		Button btn = new Button("Register");
		btn.setStyle(BUTTON_STYLE);
		showHandCursor(btn);
		
		Button btnLogin=new Button("Login");
		btnLogin.setStyle(BUTTON_STYLE);
		showHandCursor(btnLogin);
		
	
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().addAll(btn,btnLogin);
		grid.add(hbBtn, 1, 28);
		
		final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				LoginScene.displayLoginScene(primaryStage);
				
			}
		});
        
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
            			actiontarget.setText(INVALID_CREDENTIALS);
            		}
            	}
            	else{
            		actiontarget.setFill(Color.FIREBRICK);
        			actiontarget.setText(PASSWORD_MISMATCH);
            	}
            }else{
            	actiontarget.setFill(Color.FIREBRICK);
    			actiontarget.setText(INVALID_CRITERIA);
        	}
            }
            	
            });
        
        Scene registerScene = new Scene(grid, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		primaryStage.setScene(registerScene);
		primaryStage.show();
		
	}
	/**
	 * The below method is used to match password
	 * A password should match following criteria 
	 * 1)Should have atleast 1 lower and upper case character[a-z][A-Z]
	 * 2)Should have atleast 1 number[0-9]
	 * 3)It Should have atleast one special character [!,@,#,$,%,^,&,*]
	 * 4)Length should be between 6-20 characters
	 * @Given A string which is a password 
	 * @return A boolean whether it  matches the password criteria or not
	 */


	
	 public static boolean validate(final String password){
		  Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		  java.util.regex.Matcher matcher = pattern.matcher(password);
		  return matcher.matches();

	  }
	
}
