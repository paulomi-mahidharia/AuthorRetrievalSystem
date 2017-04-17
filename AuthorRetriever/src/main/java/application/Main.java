package application;

import com.neu.msd.AuthorRetriever.util.SceneStack;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The below class defines a main class.This class is used to launch the application and run the desktop app.
 * This is the main driver class that is used to run the program.
 */
@SuppressWarnings({"restriction"})
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			LoginScene.displayLoginScene(primaryStage);
			SceneStack.createSceneStack();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
