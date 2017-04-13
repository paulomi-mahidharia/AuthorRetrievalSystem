package com.neu.msd.AuthorRetriever.util;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

@SuppressWarnings("restriction")
public final class NavigationBar {
	
	public static HBox getHeaderPane(){
		
		HBox hbox = new HBox();
		
		hbox.setSpacing(300);
		
		Button btnBack = new Button("Back");
		
		Text scenetitle = new Text("SEARCH AUTHORS");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		scenetitle.setFill(Color.FIREBRICK);
		
		Button btnLogout = new Button("Logout");
		
		
		hbox.getChildren().addAll(btnBack, scenetitle, btnLogout);
		
		return hbox;
	}
}
