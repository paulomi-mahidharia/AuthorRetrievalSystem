package com.neu.msd.AuthorRetriever.util;

import static com.neu.msd.AuthorRetriever.constants.SceneContants.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public final class SearchToolTip {

	public static Scene getSearchToolTip(){
		
		BorderPane tipBox = new BorderPane();
		tipBox.setMinSize(800, 600);
		
		Text searchInfoTitle = new Text("HOW TO USE SEARCH PAGE?");
		searchInfoTitle.setFont(Font.font(FONT_TYPE, FontWeight.BOLD, TITLE_FONT_SIZE));
		searchInfoTitle.setFill(Color.FIREBRICK);
		
		HBox titleBox = new HBox();
		titleBox.setAlignment(Pos.CENTER);
		titleBox.getChildren().add(searchInfoTitle);
		titleBox.setPadding(new Insets(SCENE_GRID_PADDING, 0, SCENE_GRID_PADDING, 0));
		
		Image content = new Image("file:SearchTip.png");
		ImageView contentView = new ImageView(content);
		contentView.setFitHeight(550);
		contentView.setFitWidth(700);
		
		tipBox.setTop(titleBox);
		tipBox.setCenter(contentView);
		tipBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene scene = new Scene(tipBox, 800, 600, Color.BEIGE);
		return scene;
	}
}
