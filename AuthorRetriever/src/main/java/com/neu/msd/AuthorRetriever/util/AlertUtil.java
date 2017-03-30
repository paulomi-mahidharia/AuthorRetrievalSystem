package com.neu.msd.AuthorRetriever.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings({ "restriction" })
public class AlertUtil {
	public static void displayAlert(String title, String header, String description){
		//Display Error Message
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(description);

		alert.showAndWait();
	}
}
