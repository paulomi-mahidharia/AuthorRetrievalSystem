package application;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import com.neu.msd.AuthorRetriever.util.SceneStack;

import javafx.application.Application;
import javafx.stage.Stage;

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
