package com.neu.msd.AuthorRetriever.util;

import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.SEARCH_AUTHORS;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.LOGIN;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.LOGIN_TITLE;
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
import javafx.stage.Modality;
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
		
		Button btnSearchInfo = new Button("INFO");
		btnSearchInfo.setStyle("-fx-border-color: #b22222");
		btnSearchInfo.setVisible(false);
		if(view == SEARCH) btnSearchInfo.setVisible(true);
		
		btnSearchInfo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Disable current step
				Stage dialog = new Stage();

				// populate dialog with controls.
				dialog.setScene(SearchToolTip.getSearchToolTip());
				dialog.setResizable(false);
				dialog.initOwner(primaryStage);
				dialog.initModality(Modality.APPLICATION_MODAL); 
				dialog.showAndWait();
			}	
		});
		
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
		hbox.getChildren().addAll(btnSearchInfo, btnShortList, btnLogout);
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
		
		case LOGIN: 
			scenetitle = new Text(LOGIN_TITLE);
			break;
		
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
