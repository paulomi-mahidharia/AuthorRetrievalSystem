package application;

import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.PROGRESS_COLOR;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.PROGRESS_INDICATOR_DIMENSION;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.NO_SELECTED_AUTHOR_REMOVE;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.NO_SELECTED_AUTHOR_PROFILE;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.ERROR_RETRIEVING_AUTHOR;
import static com.neu.msd.AuthorRetriever.util.HandCursor.showHandCursor;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.AUTHORNAME;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.URL;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.UNIVERSITY;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SERIALNO;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.AuthorPaper;
import com.neu.msd.AuthorRetriever.model.Conference;
import com.neu.msd.AuthorRetriever.service.AuthorInfoService;
import com.neu.msd.AuthorRetriever.service.AuthorInfoServiceImpl;
import com.neu.msd.AuthorRetriever.service.ExportResult;
import com.neu.msd.AuthorRetriever.service.ExportResultPdfImpl;
import com.neu.msd.AuthorRetriever.service.UserService;
import com.neu.msd.AuthorRetriever.service.UserServiceImpl;
import com.neu.msd.AuthorRetriever.util.AlertUtil;
import com.neu.msd.AuthorRetriever.util.NavigationBar;
import com.neu.msd.AuthorRetriever.util.SceneStack;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 * The page is use to display a list of short listed authors using javaFX
 * The page has the following functionality .We can remove author export the list of author as pdf.
 * @Given A scene that consist of list of short listed authors with primary stage in it
 * @returns A scene that contains short listed authors
 */
@SuppressWarnings({ "rawtypes", "restriction", "unchecked" })
public class ShortListAuthor {
	
	private static List<Author> shortlistAuthor = null;
	private static Scene shortListScene = null;
	private static TableView<Author> table = null;
	
	private static StackPane stackPane = null;
	
	public static void displayShortListAuthor(Stage primaryStage){
		
		stackPane = new StackPane();
		table = new TableView<>();
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		shortlistAuthor = userServiceImpl.getAllAuthorsForUser();
		System.out.println(shortlistAuthor.size());
		
		ObservableList<Author> authorInfoData = FXCollections.observableArrayList(shortlistAuthor);
		TableColumn<Author,Number> authorId = new TableColumn(SERIALNO);
        TableColumn name = new TableColumn(AUTHORNAME);
        TableColumn authorInfo = new TableColumn(UNIVERSITY);
        TableColumn authorURL = new TableColumn(URL);
       
        authorId.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorInfo.setCellValueFactory(new PropertyValueFactory<>("affiliation"));
        authorURL.setCellValueFactory(new PropertyValueFactory<>("url"));
        
        table.getColumns().addAll(authorId, name, authorInfo, authorURL);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(authorInfoData);
		
        
        Button btnRemoveShortlistedAuthor = new Button("Remove Selected Author");
        showHandCursor(btnRemoveShortlistedAuthor);
        btnRemoveShortlistedAuthor.setStyle("-fx-border-color: #b22222");
        
        Button btnViewShortlistedAuthor = new Button("View Selected Author Profile");
        showHandCursor(btnViewShortlistedAuthor);
        btnViewShortlistedAuthor.setStyle("-fx-border-color: #b22222");
        
        Button buttonExportPdf = new Button("Export PDF");
        showHandCursor(buttonExportPdf);
	    buttonExportPdf.setStyle("-fx-border-color: #b22222");
	   
		buttonExportPdf.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
	            fileChooser.getExtensionFilters().add(extFilter);
	            File file = fileChooser.showSaveDialog(primaryStage);
	              
	            if(file != null){
	                 ExportResult exportResult=new ExportResultPdfImpl();
	                 exportResult.exportResultAsPdf(shortlistAuthor, file);
	            }
			}
		});
        
        HBox hbox = new HBox();
        hbox.getChildren().addAll(btnRemoveShortlistedAuthor, btnViewShortlistedAuthor, buttonExportPdf);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(10);
        
        BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(25, 25, 25, 25));
		
		BorderPane headerPane = NavigationBar.getHeaderPane(RESULT, primaryStage);
		headerPane.setPadding(new Insets(0, 0, 15, 0));
		bp.setTop(headerPane);
		bp.setBottom(hbox);
		bp.setCenter(table);
	
		stackPane.getChildren().add(bp);
		shortListScene = new Scene(stackPane, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		primaryStage.setScene(shortListScene);
		
        
        btnRemoveShortlistedAuthor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				UserService userService = new UserServiceImpl();
				Author selectedAuthor = table.getSelectionModel().getSelectedItem();
				
				if(selectedAuthor != null){
					System.out.println(selectedAuthor.getAuthorId());
					userService.deleteSelectedAuthor(selectedAuthor.getAuthorId());
					table.getItems().remove(selectedAuthor);
				}else{
					AlertUtil.displayAlert("Error", "Oops, you got soemthing wrong!", 
							NO_SELECTED_AUTHOR_REMOVE);
				}			
			}	
		});
        
        btnViewShortlistedAuthor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Author selectedAuthor = table.getSelectionModel().getSelectedItem();
				
				if(selectedAuthor != null){
					
					loadAuthorInformation(selectedAuthor, primaryStage, bp);
				}else{
					AlertUtil.displayAlert("Error", "Oops, you got soemthing wrong!", 
							NO_SELECTED_AUTHOR_PROFILE);
				}	
			}	
		});
        
		primaryStage.show();
	}
	
	private static void loadAuthorInformation(Author author, Stage primaryStage, BorderPane bp){
		ProgressIndicator indicator = new ProgressIndicator();
		indicator.setStyle(PROGRESS_COLOR);
		indicator.setMinSize(PROGRESS_INDICATOR_DIMENSION, PROGRESS_INDICATOR_DIMENSION);

		VBox loadingPane = new VBox();
		loadingPane.getChildren().addAll(indicator);
		loadingPane.setAlignment(Pos.CENTER);
		stackPane.getChildren().add(loadingPane);
		
		Task<Void> longTask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				
				SceneStack.pushSceneToStack(shortListScene);
	             try {
	            	AuthorInfoService authorInfoService=new AuthorInfoServiceImpl();
	         		List<AuthorPaper> paperInfo=authorInfoService.getAuthorPapers(author.getAuthorId());
	         		List<Conference>conferences=authorInfoService.getAuthorConferenceServed(author.getAuthorId());
					
	         		author.setPapers(paperInfo);
	         		author.setConferences(conferences);
	             } catch (SQLException e) {
	            	loadingPane.setVisible(false);
		    		bp.setDisable(false);
					AlertUtil.displayAlert("Error", "Oops, you got soemthing wrong!", 
							ERROR_RETRIEVING_AUTHOR);
	             }
	             
	             return null;
            }
		};

		longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				loadingPane.setVisible(false);
	    		bp.setDisable(false);
	    		AuthorScene.displayAuthorDisplayScene(author,primaryStage);
            }
        });
	    
	    indicator.progressProperty().bind(longTask.progressProperty());

	    loadingPane.setVisible(true);
	    bp.setDisable(true);
        
        new Thread(longTask).start();
	}
}


