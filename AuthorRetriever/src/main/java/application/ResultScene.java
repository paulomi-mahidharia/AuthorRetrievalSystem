package application;

import java.io.File;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.ExportResult;
import com.neu.msd.AuthorRetriever.service.ExportResultPdfImpl;
import com.neu.msd.AuthorRetriever.util.SceneStack;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.*;

@SuppressWarnings({ "rawtypes", "restriction", "unchecked" })
public class ResultScene {
	
	private static TableView table = new TableView();
	private final static int rowsPerPage = 15;
	private static List<Author>authorList=null;
	private static Scene resultScene = null;
	
	public static void displayResultScene(List<Author> resultedAuthors,Stage primaryStage){
		
		 
		System.out.println("RESULT ::: "+resultedAuthors.size());
		authorList = resultedAuthors;
		
		//Pane pane = new Pane();
		/*GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));*/
		
		Text scenetitle = new Text("RESULT");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		scenetitle.setFill(Color.FIREBRICK);
		//grid.add(scenetitle, 1, 0);
		
		table.setEditable(false);
		ObservableList<Author> authorData = FXCollections.observableArrayList(authorList);
        TableColumn srNo = new TableColumn("Sr. No.");
        TableColumn author = new TableColumn("Author");
       
        PropertyValueFactory<Author,Hyperlink> rmbutton = new PropertyValueFactory<Author,Hyperlink>("name");
        author.setCellValueFactory(rmbutton);
        srNo.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        //table.setItems(authorData);
        TableColumn authorColumn = new TableColumn("AuthorInfo");
        
        //table.setItems(authorData);
        table.getColumns().addAll(srNo, author, authorColumn);
        
        //grid.add(table, 1, 2);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(5);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(90);
        ColumnConstraints col3Constraints = new ColumnConstraints();
        col3Constraints.setPercentWidth(5);
        //grid.getColumnConstraints().addAll(col1Constraints, col2Constraints, col3Constraints);
        
        Button btnBackToSearch = new Button("Search Page");
       
		/*HBox hbBtn = new HBox(20);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(btnBackToSearch);
		grid.add(hbBtn, 1, 15);*/
		
		/*Scene resultScene = new Scene(grid, 1000, 1000, Color.BEIGE);
		primaryStage.setScene(resultScene);
		primaryStage.show();*/
		
        

		
		btnBackToSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
    			primaryStage.setScene(SceneStack.getSceneAtTopOfStack());
    			primaryStage.show();
			}	
		});
		//grid.add(hbBtn, 1, 18);
		
	    Button buttonExportPdf = new Button("Export PDF");
	   
		/*HBox hbbuttonExportPdf= new HBox(20);
		hbbuttonExportPdf.setAlignment(Pos.BOTTOM_CENTER);
		hbbuttonExportPdf.getChildren().add(buttonExportPdf);
		//grid.add(hbbuttonExportPdf, 1, 17);
		*/
		buttonExportPdf.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
	            fileChooser.getExtensionFilters().add(extFilter);
	            File file = fileChooser.showSaveDialog(primaryStage);
	              
	            if(file != null){
	                 ExportResult exportResult=new ExportResultPdfImpl();
	                 exportResult.exportResultAsPdf(authorList, file);
	            }
			}
		});
		
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10, 20, 10, 20));
		bp.setTop(btnBackToSearch);
		bp.setLeft(buttonExportPdf);
		ResultScene resultScenePaginate=new ResultScene();
		
		bp.setCenter(resultScenePaginate.paginate());
		resultScene = new Scene(bp, 1000, 1000, Color.BEIGE);
		resultScene.getStylesheets().add("/home/rushi/MSDPRoject/team3/AuthorRetriever/CSS/table.css");
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
	}
		
	private Pagination paginate(){
		
		Pagination pagination = new Pagination((authorList.size() / rowsPerPage + 1), 0);
		pagination.setPageFactory(this::createPage);
		return pagination;
	}
	
    private Node createPage(int pageIndex) {

	   int fromIndex = pageIndex * rowsPerPage;
	   int toIndex = Math.min(fromIndex + rowsPerPage,authorList.size());
	   table.setItems(FXCollections.observableArrayList(authorList.subList(fromIndex, toIndex)));
	   return new BorderPane(table);
    }
}
