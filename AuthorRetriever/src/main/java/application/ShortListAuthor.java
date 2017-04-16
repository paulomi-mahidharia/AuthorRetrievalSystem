package application;

import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.NO_SELECTED_AUTHOR_REMOVE;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.NO_SELECTED_AUTHOR_PROFILE;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.ERROR_RETRIEVING_AUTHOR;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.UserService;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@SuppressWarnings({ "rawtypes", "restriction", "unchecked" })
public class ShortListAuthor {
	
	private static List<Author> shortlistAuthor = null;
	private static Scene shortListScene = null;
	private static TableView<Author> table = null;
	
	public static void displayShortListAuthor(Stage primaryStage){
		
		table = new TableView<>();
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		shortlistAuthor = userServiceImpl.getAllAuthorsForUser();
		System.out.println(shortlistAuthor.size());
		
		ObservableList<Author> authorInfoData = FXCollections.observableArrayList(shortlistAuthor);
		TableColumn<Author,Number> authorId = new TableColumn("Serial No.");
        TableColumn name = new TableColumn("Author Name");
        TableColumn authorInfo = new TableColumn("University");
       
        authorId.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
      
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorInfo.setCellValueFactory(new PropertyValueFactory<>("affiliation"));
        
        table.getColumns().addAll(authorId, name, authorInfo);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(authorInfoData);
		
        
        Button btnRemoveShortlistedAuthor = new Button("Remove Selected Author");
        Button btnViewShortlistedAuthor = new Button("View Selected Author Profile");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(btnRemoveShortlistedAuthor, btnViewShortlistedAuthor);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(10);
        
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
					try {
						SceneStack.pushSceneToStack(shortListScene);
						AuthorScene.displayAuthorDisplayScene(selectedAuthor, primaryStage);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						AlertUtil.displayAlert("Error", "Oops, you got soemthing wrong!", 
								ERROR_RETRIEVING_AUTHOR);
					}
				}else{
					AlertUtil.displayAlert("Error", "Oops, you got soemthing wrong!", 
							NO_SELECTED_AUTHOR_PROFILE);
				}	
			}	
		});
        
        BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(25, 25, 25, 25));
		
		BorderPane headerPane = NavigationBar.getHeaderPane(RESULT, primaryStage);
		headerPane.setPadding(new Insets(0, 0, 15, 0));
		bp.setTop(headerPane);
		bp.setBottom(hbox);
		bp.setCenter(table);
	
		shortListScene = new Scene(bp, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		primaryStage.setScene(shortListScene);
		primaryStage.show();
	}
}
