package application;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.util.SceneStack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

@SuppressWarnings({ "rawtypes", "restriction", "unchecked" })
public class ResultScene {
	
	private static TableView table = null;
	public static void displayResultScene(List<Author> resultedAuthors,Stage primaryStage){
		
		table = new TableView();
		
		System.out.println("RESULT ::: "+resultedAuthors.size());
		List<Author> authors = resultedAuthors;
		
		//Pane pane = new Pane();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		
		Text scenetitle = new Text("RESULT");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		scenetitle.setFill(Color.FIREBRICK);
		grid.add(scenetitle, 1, 0);
		
		TableColumn editColumn = new TableColumn("AuthorInformation");
        editColumn.setCellValueFactory(new PropertyValueFactory<Author,Hyperlink>("authorKey"));
		table.setEditable(false);
		ObservableList<Author> authorData = FXCollections.observableArrayList(authors);
        TableColumn srNo = new TableColumn("Sr. No.");
        TableColumn author = new TableColumn("Author");
       
        PropertyValueFactory<Author,Hyperlink> rmbutton = new PropertyValueFactory<Author,Hyperlink>("name");

       // srNo.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(YourTable.getItems().indexOf(column.getValue())));
        author.setCellValueFactory(rmbutton);
        srNo.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        table.setItems(authorData);
        table.getColumns().addAll(srNo, author,editColumn);
        
        grid.add(table, 1, 2);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(5);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(90);
        ColumnConstraints col3Constraints = new ColumnConstraints();
        col3Constraints.setPercentWidth(5);
        grid.getColumnConstraints().addAll(col1Constraints, col2Constraints, col3Constraints);
        
        Button btn = new Button("Search Page");
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox hbBtn = new HBox(20);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 15);
		
		Scene resultScene = new Scene(grid, 1000, 1000, Color.BEIGE);
		primaryStage.setScene(resultScene);
		primaryStage.show();
		
		table.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Author>() {
            public void onChanged(ListChangeListener.Change<? extends Author> c) {
            	Author author=null;
                for (Author p : c.getList()) {
                  author=p;
                }
             SceneStack.pushSceneToStack(resultScene);
             AuthorDispayInformationScene.displayAuthorDisplayScene(author,primaryStage);
            }
        });

		
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
    			primaryStage.setScene(SceneStack.getSceneAtTopOfStack());
    			primaryStage.show();
			}	
		});
	}	
}
