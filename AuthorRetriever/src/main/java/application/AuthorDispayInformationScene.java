package application;

import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.constants.ValidationConstants;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.SearchSimilarProfileService;
import com.neu.msd.AuthorRetriever.service.SearchSimilarProfileServiceImpl;
import com.neu.msd.AuthorRetriever.util.AlertUtil;
import com.neu.msd.AuthorRetriever.util.SceneStack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings({ "rawtypes", "restriction", "unused"})
public class AuthorDispayInformationScene {
	
	private static TableView table = null;
	public static void displayAuthorDisplayScene(Author selectedAuthor,Stage primaryStage){
	
		table = new TableView();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		Text scenetitle = new Text("Author Profile");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		scenetitle.setFill(Color.FIREBRICK);
		grid.add(scenetitle, 1, 0);
		
		Text t1 = new Text(10, 50,selectedAuthor.getName() );
		t1.setFont(new Font(20));
       
        Text t2 = new Text(10, 50,selectedAuthor.getDegree() );
		t2.setFont(new Font(20));
       
        Text t3 = new Text(10, 50,selectedAuthor.getCountry() );
		t3.setFont(new Font(20));
        grid.add(t1, 1, 2);
        grid.add(t2, 0, 3);
        grid.add(t3, 0, 4);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(5);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(90);
        ColumnConstraints col3Constraints = new ColumnConstraints();
        col3Constraints.setPercentWidth(5);
        grid.getColumnConstraints().addAll(col1Constraints, col2Constraints, col3Constraints);
        
        Button btn = new Button("Restart Search");
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox hbBtn = new HBox(20);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(btn);
		
		grid.add(hbBtn, 1, 15);
		Button btn1 = new Button("Search Similar Author");
        btn1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox hbBtn1 = new HBox(20);
		hbBtn1.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn1.getChildren().add(btn1);
		grid.add(hbBtn1, 1, 17);
		
		Scene authorDispalyScene = new Scene(grid, 1000, 1000, Color.BEIGE);
		primaryStage.setScene(authorDispalyScene);
		primaryStage.show();
		
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SearchScene.displaySearchScene(primaryStage); 
				SceneStack.flushSceneStack();
			}	
		});
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				SearchSimilarProfileService searchSimilarProfileService = new SearchSimilarProfileServiceImpl();
				try {
					List<Author> similarAuthors = searchSimilarProfileService.searchSimilarAuthorProfiles(selectedAuthor);
					if(!similarAuthors.isEmpty() || similarAuthors.size() != 0){
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
}
