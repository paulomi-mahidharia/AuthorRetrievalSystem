package com.neu.msd.AuthorRetriever.util;

import static com.neu.msd.AuthorRetriever.constants.SceneContants.AUTHOR;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.REGISTER;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.REGISTER_TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.AUTHOR_TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.FONT_TYPE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.LOGIN;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.LOGIN_TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT_TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH_TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.TITLE_FONT_SIZE;
import static com.neu.msd.AuthorRetriever.util.HandCursor.showHandCursor;

import application.LoginScene;
import application.ShortListAuthor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *The below class is used to generate headerPane for all UI components
 *@Given: A string and a stage object
 *@return:A object of BorderPane
 */
public final class NavigationBar {
	
	public static BorderPane getHeaderPane(String view, Stage primaryStage){
		
		BorderPane bp = new BorderPane();
		
		Button btnBack = new Button("Back");
		showHandCursor(btnBack);
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
		
		Image info = new Image("file:info.png");
		ImageView infoIcon = new ImageView(info);
		infoIcon.setFitHeight(30);
		infoIcon.setFitWidth(30);
		infoIcon.setVisible(false);
		
		if(view.equalsIgnoreCase(SEARCH)) infoIcon.setVisible(true);
		
		showHandCursor(infoIcon);
		infoIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		     @Override
		     public void handle(MouseEvent event) {
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
		showHandCursor(btnShortList);
		btnShortList.setStyle("-fx-border-color: #b22222");
		
		btnShortList.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SceneStack.pushSceneToStack(SceneStack.getCurrentScene());
				ShortListAuthor.displayShortListAuthor(primaryStage);
			}	
		});
		
		Button btnLogout = new Button("Logout");
		showHandCursor(btnLogout);
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
		hbox.getChildren().addAll(infoIcon, btnShortList, btnLogout);
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
		case REGISTER:
			scenetitle = new Text(REGISTER_TITLE);
			break;
		}
		scenetitle.setFont(Font.font(FONT_TYPE, FontWeight.BOLD, TITLE_FONT_SIZE));
		scenetitle.setFill(Color.FIREBRICK);
		return scenetitle;
	}
}
