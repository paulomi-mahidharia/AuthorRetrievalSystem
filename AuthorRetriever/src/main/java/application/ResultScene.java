package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.sonar.api.server.ws.WebService.SelectionMode;

import com.neu.msd.AuthorRetriever.model.Author;

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
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SelectionModel;
import javafx.stage.FileChooser;

@SuppressWarnings({ "rawtypes", "restriction" })
public class ResultScene {
	
	private static TableView table = new TableView();
	public static Scene getResultScene(List<Author> resultedAuthors,Stage primaryStage){
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

       
        table.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Author>() {
            public void onChanged(ListChangeListener.Change<? extends Author> c) {
            	Author author=null;
                for (Author p : c.getList()) {
                  author=p;
                }
             Scene authorDispayInformationScene =AuthorDispayInformationScene.getAuthorDisplayScene(author,primaryStage);
             primaryStage.setScene(authorDispayInformationScene);
				primaryStage.show();

             
            }
        });

        
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
		
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Scene searchScene = SearchScene.getSearchScene(primaryStage); 
    			primaryStage.setScene(searchScene);
    			primaryStage.show();
        		
				
			}
			
		});
		grid.add(hbBtn, 1, 15);
		
	    Button buttonExportPdf = new Button("Export PDF");
	    buttonExportPdf.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox hbbuttonExportPdf= new HBox(20);
		hbbuttonExportPdf.setAlignment(Pos.BOTTOM_CENTER);
		hbbuttonExportPdf.getChildren().add(buttonExportPdf);
		grid.add(hbbuttonExportPdf, 1, 17);
		
		buttonExportPdf.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				FileChooser fileChooser = new FileChooser();
				 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
	             fileChooser.getExtensionFilters().add(extFilter);
	             File file = fileChooser.showSaveDialog(primaryStage);
	              
	              if(file != null){
	                  SaveFile("Pdf_File", file);
	              }
				
			}
		});
		
		Scene resultScene = new Scene(grid, 1000, 1000, Color.BEIGE);
		return resultScene;
	}
	private static void  SaveFile(String content, File file){
        try {
        	
            FileWriter fileWriter = null;
             
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
       //     Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
		
}
