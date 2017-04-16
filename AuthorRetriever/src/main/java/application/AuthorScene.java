package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthorizeCallback;

import com.neu.msd.AuthorRetriever.constants.ValidationConstants;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.AuthorPaper;
import com.neu.msd.AuthorRetriever.model.Conference;
import com.neu.msd.AuthorRetriever.service.AuthorInfoService;
import com.neu.msd.AuthorRetriever.service.AuthorInfoServiceImpl;
import com.neu.msd.AuthorRetriever.service.SearchSimilarProfileService;
import com.neu.msd.AuthorRetriever.service.SearchSimilarProfileServiceImpl;
import com.neu.msd.AuthorRetriever.service.UserServiceImpl;
import com.neu.msd.AuthorRetriever.util.AlertUtil;
import com.neu.msd.AuthorRetriever.util.NavigationBar;
import com.neu.msd.AuthorRetriever.util.SceneStack;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.application.HostServices;

import static com.neu.msd.AuthorRetriever.constants.SceneContants.AUTHOR;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.RESTART_SEARCH;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.SEARCH_SIMILAR_AUTHOR;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.SHORTLIST_AUTHOR;

@SuppressWarnings({ "rawtypes", "restriction", "unused"})
public class AuthorScene {
	
	
	private static TableView table = null;
	public static void displayAuthorDisplayScene(Author selectedAuthor,Stage primaryStage) throws SQLException{
	
		table = new TableView();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		BorderPane headerPane = NavigationBar.getHeaderPane(AUTHOR, primaryStage);
		grid.add(headerPane, 1, 0);
		AuthorInfoService authorInfoService=new AuthorInfoServiceImpl();
		List<AuthorPaper> paperInfo=authorInfoService.getAuthorPapers(selectedAuthor.getAuthorId());
		List<Conference>conferences=authorInfoService.getAuthorConferenceServed(selectedAuthor.getAuthorId());
		
		TableView createPaperInfoTable=createPaperInfoTable(paperInfo);
		TableView createConferenceInfoTable=createConferenceInfoTable(conferences);
		
		Text t1 = new Text(10, 50,selectedAuthor.getName());
		t1.setFont(new Font(20));
       
        Text t2 = new Text(10, 50, "Affiliated University: " +selectedAuthor.getAffiliation());
		t2.setFont(new Font(16));
		 
        Text t3 = new Text(10, 50, "URL: "+ selectedAuthor.getUrl());
		t3.setFont(new Font(16));
		
        grid.add(t1, 1, 2);
        grid.add(t2, 1, 3);
        grid.add(t3, 1, 4);
        grid.add(createPaperInfoTable, 1,6);
        grid.add(createConferenceInfoTable, 1,9);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(5);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(90);
        ColumnConstraints col3Constraints = new ColumnConstraints();
        col3Constraints.setPercentWidth(5);
        grid.getColumnConstraints().addAll(col1Constraints, col2Constraints, col3Constraints);
        
        Button btnResetSearch = new Button(RESTART_SEARCH);
        btnResetSearch.setStyle("-fx-border-color: #b22222");
        btnResetSearch.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button btnSimilarAuthors = new Button(SEARCH_SIMILAR_AUTHOR);
        btnSimilarAuthors.setStyle("-fx-border-color: #b22222");
        btnSimilarAuthors.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
        
        Button btnShortListAuthor = new Button(SHORTLIST_AUTHOR);
        btnShortListAuthor.setStyle("-fx-border-color: #b22222");
        btnShortListAuthor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
        Button btnaddSelectedAuthor =new Button(SHORTLIST_AUTHOR);
        btnaddSelectedAuthor.setStyle("-fx-border-color: #b22222");
        btnaddSelectedAuthor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        HBox hbBtn = new HBox(20);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().addAll(btnResetSearch, btnSimilarAuthors,btnShortListAuthor);
		
		grid.add(hbBtn, 1, 15);
		
		Scene authorDispalyScene = new Scene(grid, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		SceneStack.setCurrentScene(authorDispalyScene);
		primaryStage.setScene(authorDispalyScene);
		primaryStage.show();
		
		btnShortListAuthor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				UserServiceImpl userServiceImpl=new UserServiceImpl();
				List<Author> selectedAuthorList=new ArrayList<>();
				selectedAuthorList.add(selectedAuthor);
				userServiceImpl.addSelectedAuthors(selectedAuthorList);
				AlertUtil alertUtil=new AlertUtil();
				String message="Succefully saved" +selectedAuthor.getName()+ "to your shortlisted author list";
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
				// TODO Auto-generated method stub
				SearchSimilarProfileService searchSimilarProfileService = new SearchSimilarProfileServiceImpl();
				try {
					List<Author> similarAuthors = searchSimilarProfileService.searchSimilarAuthorProfiles(selectedAuthor);
					
					System.out.println("similarAuthors :::" +similarAuthors.size());
					if(!similarAuthors.isEmpty() && similarAuthors.size() != 0){
						ResultScene.displayResultScene(similarAuthors, primaryStage);
						SceneStack.pushSceneToStack(authorDispalyScene);
					}else{
						AlertUtil.displayAlert("Error", 
								"Oops, you got soemthing wrong!", 
								ValidationConstants.NO_AUTHORS_FOUND);
						return;
					}			
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	private static TableView createConferenceInfoTable(List<Conference> conferences) {
		// TODO Auto-generated method stub
		
		TableView table = new TableView<>();
		System.out.println(conferences.size());
		ObservableList<Conference> confInfoData = FXCollections.observableArrayList(conferences);
		TableColumn<Conference,Number> conferenceId = new TableColumn("Serial No.");
        TableColumn year = new TableColumn("Year");
        TableColumn name = new TableColumn("Name");
        TableColumn title = new TableColumn("Title");
        
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
		TableColumn<AuthorPaper,Number> paperId = new TableColumn("Serial No.");
        TableColumn confName = new TableColumn("Conferance Title");
        TableColumn paperTitle = new TableColumn("Paper Title");
        TableColumn year = new TableColumn("Year");
        TableColumn url = new TableColumn("URL");
        
        paperId.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        confName.setCellValueFactory(new PropertyValueFactory<>("confName"));
        paperTitle.setCellValueFactory(new PropertyValueFactory<>("paperTitle"));
        year.setCellValueFactory(new PropertyValueFactory<>("Year"));
        url.setCellValueFactory(new PropertyValueFactory<>("url"));
        
        table.getColumns().addAll(paperId, confName, paperTitle,year,url);
        //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        table.setItems(paperInfoData);
		
        return table;
	}
}
