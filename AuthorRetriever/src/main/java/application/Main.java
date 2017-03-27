package application;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.SearchServiceImpl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene loginScene = LoginScene.getLoginScene(primaryStage);
			primaryStage.setScene(loginScene);
			primaryStage.show();
			
			Author author = new Author();
			author.setAuthorId(575214);
			
			SearchServiceImpl sl = new SearchServiceImpl();
			sl.searchSimilarAuthorProfiles(author);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
