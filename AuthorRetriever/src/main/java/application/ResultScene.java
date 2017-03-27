package application;

import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ResultScene {
	
	private static TableView table = new TableView();
	public static Scene getResultScene(List<Author> resultedAuthors){
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
		
		table.setEditable(false);
		 
        TableColumn srNo = new TableColumn("Sr. No.");
        TableColumn author = new TableColumn("Author");
        
        table.getColumns().addAll(srNo, author);
        
        grid.add(table, 1, 2);
        
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(5);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(90);
        ColumnConstraints col3Constraints = new ColumnConstraints();
        col3Constraints.setPercentWidth(5);
        grid.getColumnConstraints().addAll(col1Constraints, col2Constraints, col3Constraints);
		
		Scene resultScene = new Scene(grid, 1000, 1000, Color.BEIGE);
		
		return resultScene;
	}
	
}
