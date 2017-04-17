package com.neu.msd.AuthorRetriever.util;

import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.SEARCH_AUTHORS;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.AUTHOR;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.AUTHOR_TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT_TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH_TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.FONT_TYPE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.TITLE_FONT_SIZE;

import application.LoginScene;
import application.ShortListAuthor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

@SuppressWarnings("restriction")
public final class NavigationBar {
	
	public static BorderPane getHeaderPane(String view, Stage primaryStage){
		
		BorderPane bp = new BorderPane();
		
		Button btnBack = new Button("Back");
		btnBack.setStyle("-fx-border-color: #b22222");
		btnBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.setScene(SceneStack.getSceneAtTopOfStack());
				primaryStage.show();	
			}
		});
		
		Text scenetitle = getSceneTitle(view);
		
		Button btn = new Button(SEARCH_AUTHORS);
		btn.setStyle("-fx-border-color: #b22222");
		
		Button btnShortList = new Button("ShortListed Authors");
		btnShortList.setStyle("-fx-border-color: #b22222");
		
		btnShortList.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SceneStack.pushSceneToStack(SceneStack.getCurrentScene());
				ShortListAuthor.displayShortListAuthor(primaryStage);
			}	
		});
		
		Button btnLogout = new Button("Logout");
		btnLogout.setStyle("-fx-border-color: #b22222");
		
		btnLogout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				LoginScene.displayLoginScene(primaryStage);
				SceneStack.flushSceneStack();
			}
		});
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(btnShortList, btnLogout);
		hbox.setSpacing(10);
		
		if(view.equals(SEARCH))
			btnBack.setDisable(true);
			
		bp.setLeft(btnBack);
		bp.setCenter(scenetitle);
		bp.setRight(hbox);
		
		return bp;
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
			
		case AUTHOR:
			scenetitle = new Text(AUTHOR_TITLE);
			break;
		}
		scenetitle.setFont(Font.font(FONT_TYPE, FontWeight.BOLD, TITLE_FONT_SIZE));
		scenetitle.setFill(Color.FIREBRICK);
		return scenetitle;
	}
}
