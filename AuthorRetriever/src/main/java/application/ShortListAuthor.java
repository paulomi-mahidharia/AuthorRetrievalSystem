package application;

import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;

import java.util.ArrayList;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.Conference;
import com.neu.msd.AuthorRetriever.service.UserService;
import com.neu.msd.AuthorRetriever.service.UserServiceImpl;
import com.neu.msd.AuthorRetriever.util.AlertUtil;
import com.neu.msd.AuthorRetriever.util.NavigationBar;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShortListAuthor {
	static List<Author> shortlistAuthor=null;
	private static Scene shortListScene = null;
	public static void displayShortListAuthor(Stage primaryStage){
		TableView<Author> table = new TableView<>();
		UserServiceImpl userServiceImpl=new UserServiceImpl();
		List<Author>shortlistAuthor=userServiceImpl.getAllAuthorsForUser();
		System.out.println(shortlistAuthor.size());
		ObservableList<Author> authorInfoData = FXCollections.observableArrayList(shortlistAuthor);
		TableColumn<Author,Number> authorId = new TableColumn("#");
        TableColumn name = new TableColumn("name");
        TableColumn authorInfo = new TableColumn("AuthorInfo");
        
        
        authorId.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
      
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorInfo.setCellValueFactory(new PropertyValueFactory<>("affiliation"));
       
        
        
        table.getColumns().addAll(authorId, name, authorInfo);
        
        table.setItems(authorInfoData);
		
        
        Button buttonSearch = new Button("Remove Author");
        
        buttonSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				UserService userService=new UserServiceImpl();
				Author author=table.getSelectionModel().getSelectedItem();
				System.out.println(author.getAuthorId());
				userService.deleteSelectedAuthor(author.getAuthorId());
				table.getItems().remove(author);
								
			}	
		});

        
        BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(25, 25, 25, 25));
		
		BorderPane headerPane = NavigationBar.getHeaderPane(RESULT, primaryStage);
		headerPane.setPadding(new Insets(0, 0, 15, 0));
		bp.setTop(headerPane);
		bp.setBottom(buttonSearch);
		bp.setCenter(table);
	
		shortListScene = new Scene(bp, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		
		

		primaryStage.setScene(shortListScene);
		primaryStage.show();

		
	}

}
