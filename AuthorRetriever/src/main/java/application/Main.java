package application;

import com.neu.msd.AuthorRetriever.util.SceneStack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings({"restriction"})
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene loginScene = LoginScene.getLoginScene(primaryStage);
			primaryStage.setScene(loginScene);
			primaryStage.show();

			SceneStack.createSceneStack();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
