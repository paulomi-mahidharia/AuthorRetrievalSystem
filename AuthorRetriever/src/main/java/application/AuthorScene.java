package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthorizeCallback;

import org.controlsfx.control.HyperlinkLabel;

import com.neu.msd.AuthorRetriever.constants.ValidationConstants;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.AuthorPaper;
import com.neu.msd.AuthorRetriever.model.Conference;
import com.neu.msd.AuthorRetriever.service.AuthorInfoService;
import com.neu.msd.AuthorRetriever.service.AuthorInfoServiceImpl;
import com.neu.msd.AuthorRetriever.service.SearchService;
import com.neu.msd.AuthorRetriever.service.SearchServiceImpl;
import com.neu.msd.AuthorRetriever.service.SearchSimilarProfileService;
import com.neu.msd.AuthorRetriever.service.SearchSimilarProfileServiceImpl;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.application.HostServices;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ProgressIndicator;

import static com.neu.msd.AuthorRetriever.constants.SceneContants.AUTHOR;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.PROGRESS_INDICATOR_DIMENSION;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.ALERT_ERROR;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.ALERT_HEADER;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.NO_AUTHORS_FOUND;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.SQL_FAILURE;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.PROGRESS_COLOR;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.RESTART_SEARCH;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.SEARCH_SIMILAR_AUTHOR;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.SHORTLIST_AUTHOR;
import static com.neu.msd.AuthorRetriever.util.HandCursor.showHandCursor;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.AUTHORNAME;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.URL;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.UNIVERSITY;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SERIALNO;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.YEAR;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.NAME;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.CONFERENCE_TITLE;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.PAPER_TITLE;;
/**
 * Below class is use to display author details.This class is used to build UI for author information
 * The scene generated from this class displays on UI Author name , author university URL and the papers 
 * he has published and the conference in which he chaired.
 * 
 * @Given Author object and a stage to display the UI options for user
 * @Returns :This is a void method
 */

@SuppressWarnings({ "rawtypes", "restriction", "unused"})
public class AuthorScene {
	
	private static WebView browser = null;
    private static WebEngine webEngine = null;
	private static TableView table = null;
	
	private static StackPane stackPane;
	private static List<Author> similarAuthors;
	
	/**
	 * @Given Author OBject and UI option to display scene
	 * @return:This is a void method  
	 *	The below static method is use to display UI object using javaFX.This method displays author Information
	 */
	public static void displayAuthorDisplayScene(Author selectedAuthor,Stage primaryStage) {
	
		stackPane = new StackPane();
		table = new TableView();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		BorderPane headerPane = NavigationBar.getHeaderPane(AUTHOR, primaryStage);
		grid.add(headerPane, 1, 0);
		
		List<AuthorPaper> paperInfo = selectedAuthor.getPapers();
		List<Conference> conferences = selectedAuthor.getConferences();
		TableView createPaperInfoTable=createPaperInfoTable(paperInfo);
		TableView createConferenceInfoTable=createConferenceInfoTable(conferences);
		
		Text t1 = new Text(10, 50,selectedAuthor.getName());
		t1.setFont(new Font(24));
       
        Text t2 = new Text(10, 50, selectedAuthor.getAffiliation());
		t2.setFont(new Font(16));
		
		BorderPane authorInfoPane = new BorderPane();
		authorInfoPane.setLeft(t2);
		
		if(selectedAuthor.getUrl().equals("None")){
			Text t3 = new Text(10, 50, selectedAuthor.getUrl());
			t3.setFont(new Font(16));
			authorInfoPane.setRight(t3);
		}else{
			final Hyperlink hpl = new Hyperlink(selectedAuthor.getUrl());
	        hpl.setFont(new Font(16));
	        hpl.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	browser = new WebView();
	            	webEngine = browser.getEngine();
	                webEngine.load(selectedAuthor.getUrl());
	                Stage stageForURL = new Stage();
	                Scene bro = new Scene(browser, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
	                stageForURL.setScene(bro);
	                stageForURL.show();
	            }
	        });
	        authorInfoPane.setRight(hpl);
		}
		
        grid.add(t1, 1, 2);
        grid.add(authorInfoPane, 1, 4);
        
        Text table1 = new Text(10, 50, "Publications by "+selectedAuthor.getName()+":");
        table1.setFont(new Font(16));
        
        Text table2 = new Text(10, 50, "Committees served on by "+selectedAuthor.getName()+":");
        table2.setFont(new Font(16));
        
        grid.add(table1, 1, 5);
        grid.add(createPaperInfoTable, 1,6);
        grid.add(table2, 1, 8);
        grid.add(createConferenceInfoTable, 1,9);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(5);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(90);
        ColumnConstraints col3Constraints = new ColumnConstraints();
        col3Constraints.setPercentWidth(5);
        grid.getColumnConstraints().addAll(col1Constraints, col2Constraints, col3Constraints);
        
        Button btnResetSearch = new Button(RESTART_SEARCH);
        showHandCursor(btnResetSearch);
        btnResetSearch.setStyle("-fx-border-color: #b22222");
        btnResetSearch.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button btnSimilarAuthors = new Button(SEARCH_SIMILAR_AUTHOR);
        showHandCursor(btnSimilarAuthors);
        btnSimilarAuthors.setStyle("-fx-border-color: #b22222");
        btnSimilarAuthors.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button btnShortListAuthor = new Button(SHORTLIST_AUTHOR);
        showHandCursor(btnShortListAuthor);
        btnShortListAuthor.setStyle("-fx-border-color: #b22222");
        btnShortListAuthor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
        Button btnaddSelectedAuthor =new Button(SHORTLIST_AUTHOR);
        showHandCursor(btnaddSelectedAuthor);
        btnaddSelectedAuthor.setStyle("-fx-border-color: #b22222");
        btnaddSelectedAuthor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        HBox hbBtn = new HBox(20);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().addAll(btnResetSearch, btnSimilarAuthors,btnShortListAuthor);
		
		grid.add(hbBtn, 1, 15);
		stackPane.getChildren().add(grid);
		
		Scene authorDisplayScene = new Scene(stackPane, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		SceneStack.setCurrentScene(authorDisplayScene);
		primaryStage.setScene(authorDisplayScene);
		
		
		btnShortListAuthor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				UserServiceImpl userServiceImpl=new UserServiceImpl();
				List<Author> selectedAuthorList=new ArrayList<>();
				selectedAuthorList.add(selectedAuthor);
				userServiceImpl.addSelectedAuthors(selectedAuthorList);
				AlertUtil alertUtil=new AlertUtil();
				String message="Succefully saved " +selectedAuthor.getName()+ " to your shortlisted author list";
				alertUtil.displayAlert("Success", selectedAuthor.getName(),message);
				
			}	
		});
		
		btnResetSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SearchScene.displaySearchScene(primaryStage); 
				SceneStack.flushSceneStack();
			}	
		});
		
		btnSimilarAuthors.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				searchSimilarAuthorsTask(selectedAuthor, primaryStage, authorDisplayScene, grid);
				
			}
		});
		
		primaryStage.show();
	}
	
	@SuppressWarnings("unchecked")
	private static void searchSimilarAuthorsTask(Author selectedAuthor, Stage primaryStage, Scene authorDisplayScene, GridPane grid){
		ProgressIndicator indicator = new ProgressIndicator();
		indicator.setStyle(PROGRESS_COLOR);
		indicator.setMinSize(PROGRESS_INDICATOR_DIMENSION, PROGRESS_INDICATOR_DIMENSION);
		
		VBox loadingPane = new VBox();
		loadingPane.getChildren().addAll(indicator);
		loadingPane.setAlignment(Pos.CENTER);
		stackPane.getChildren().add(loadingPane);
		
		Task longTask = new Task<Void>() {
        	
            @Override
            protected Void call() throws Exception {
            	SearchSimilarProfileService searchSimilarProfileService = new SearchSimilarProfileServiceImpl();
				try {
					similarAuthors = searchSimilarProfileService.searchSimilarAuthorProfiles(selectedAuthor);					
				} catch (SQLException e) {
					grid.setDisable(false);
                	loadingPane.setVisible(false);
					AlertUtil.displayAlert("Error", 
							"Oops, you got soemthing wrong!", 
							"Unable to retrieve similar authors!");
				}
				return null;
            }
        };
        
        longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
            	grid.setDisable(false);
            	loadingPane.setVisible(false);
            	if(!similarAuthors.isEmpty() && similarAuthors.size() != 0){
            		SceneStack.pushSceneToStack(authorDisplayScene);
    				ResultScene.displayResultScene(similarAuthors, primaryStage);
    			}else{
    				AlertUtil.displayAlert(ALERT_ERROR, ALERT_HEADER, NO_AUTHORS_FOUND);
    				return;
    			}
            }
        });
        
        indicator.progressProperty().bind(longTask.progressProperty());
        loadingPane.setVisible(true);
	    grid.setDisable(true);
        
        new Thread(longTask).start();
	}
	
	private static TableView createConferenceInfoTable(List<Conference> conferences) {
		// TODO Auto-generated method stub
		
		TableView table = new TableView<>();
		System.out.println(conferences.size());
		ObservableList<Conference> confInfoData = FXCollections.observableArrayList(conferences);
		TableColumn<Conference,Number> conferenceId = new TableColumn(SERIALNO);
        TableColumn year = new TableColumn(YEAR);
        TableColumn name = new TableColumn(NAME);
        TableColumn title = new TableColumn(TITLE);
        
        conferenceId.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
      
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        
        table.getColumns().addAll(conferenceId, year, name,title);
        //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(confInfoData);
		
        return table;
	}
	private static TableView createPaperInfoTable(List<AuthorPaper> paperInfo) {
		TableView table = new TableView<>();
		
		ObservableList<AuthorPaper> paperInfoData = FXCollections.observableArrayList(paperInfo);
		TableColumn<AuthorPaper,Number> paperId = new TableColumn(SERIALNO);
        TableColumn confName = new TableColumn(CONFERENCE_TITLE);
        TableColumn paperTitle = new TableColumn(PAPER_TITLE);
        TableColumn year = new TableColumn(YEAR);
        TableColumn url = new TableColumn(URL);
        
        paperId.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        confName.setCellValueFactory(new PropertyValueFactory<>("confName"));
        paperTitle.setCellValueFactory(new PropertyValueFactory<>("paperTitle"));
        year.setCellValueFactory(new PropertyValueFactory<>("Year"));
        url.setCellValueFactory(new PropertyValueFactory<>("url"));
        
        table.getColumns().addAll(paperId, confName, paperTitle, year, url);
        //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        table.setItems(paperInfoData);
		
        return table;
	}
}
