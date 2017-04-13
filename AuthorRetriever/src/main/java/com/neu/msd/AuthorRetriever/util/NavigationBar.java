package com.neu.msd.AuthorRetriever.util;

import application.LoginScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH_TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT_TITLE;

@SuppressWarnings("restriction")
public final class NavigationBar {
	
	public static HBox getHeaderPane(String view, Stage primaryStage){
		
		HBox hbox = new HBox();
		
		hbox.setSpacing(300);
		
		Button btnBack = new Button("Back");
		
		btnBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.setScene(SceneStack.getSceneAtTopOfStack());
				primaryStage.show();	
			}
		});
		
		Text scenetitle = getSceneTitle(view);
		
		Button btnLogout = new Button("Logout");
		
		btnLogout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				LoginScene.displayLoginScene(primaryStage);
				SceneStack.flushSceneStack();
			}
		});
		
		if(view.equals(SEARCH))
			btnBack.setDisable(true);
			
		hbox.getChildren().addAll(btnBack, scenetitle, btnLogout);
		return hbox;
	}

	private static Text getSceneTitle(String view) {
		// TODO Auto-generated method stub
		
		Text scenetitle = null;
		
		switch(view){
		
		case SEARCH: 
			scenetitle = new Text(SEARCH_TITLE);
			break;
		
		case RESULT:
			scenetitle = new Text(RESULT_TITLE);
			break;
		}
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		scenetitle.setFill(Color.FIREBRICK);
		return scenetitle;
	}
}
