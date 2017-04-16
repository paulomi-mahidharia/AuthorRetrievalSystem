package application;

import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.ERROR_RETRIEVING_AUTHOR;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.ExportResult;
import com.neu.msd.AuthorRetriever.service.ExportResultPdfImpl;
import com.neu.msd.AuthorRetriever.util.AlertUtil;
import com.neu.msd.AuthorRetriever.util.NavigationBar;
import com.neu.msd.AuthorRetriever.util.SceneStack;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

@SuppressWarnings({ "rawtypes", "restriction", "unchecked" })
public class ResultScene {
	
	private static TableView table = null;
	private final static int rowsPerPage = 20;
	private static List<Author>authorList=null;
	private static Scene resultScene = null;
	
	public static void displayResultScene(List<Author> resultedAuthors,Stage primaryStage){
		
		table = new TableView(); 
		System.out.println("RESULT ::: "+resultedAuthors.size());
		authorList = resultedAuthors;
        
		table.setEditable(true);
		ObservableList<Author> authorData = FXCollections.observableArrayList(authorList);
        TableColumn<Author,Number> srNo = new TableColumn("Serial No.");
        TableColumn author = new TableColumn("Author");

        PropertyValueFactory<Author,Hyperlink> rmbutton = new PropertyValueFactory<Author,Hyperlink>("name");
        author.setCellValueFactory(rmbutton);
        srNo.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        srNo.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
      
        TableColumn authorColumn = new TableColumn("University");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("affiliation"));
        
        TableColumn authorUrlColumn = new TableColumn("URL");
        authorUrlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        
        table.getColumns().addAll(srNo, author, authorColumn, authorUrlColumn);
        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        Button btnBackToSearch = new Button("Search Page");
        btnBackToSearch.setStyle("-fx-border-color: #b22222");
       
		btnBackToSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
    			primaryStage.setScene(SceneStack.getSceneAtTopOfStack());
    			primaryStage.show();
			}	
		});

		
	    Button buttonExportPdf = new Button("Export PDF");
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
	                 exportResult.exportResultAsPdf(authorList, file);
	            }
			}
		});
		
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(25, 25, 25, 25));
		BorderPane headerPane = NavigationBar.getHeaderPane(RESULT, primaryStage);
		headerPane.setPadding(new Insets(0, 0, 15, 0));
		bp.setTop(headerPane);
		bp.setBottom(buttonExportPdf);
		ResultScene resultScenePaginate=new ResultScene();
		
		bp.setCenter(resultScenePaginate.paginate());

		resultScene = new Scene(bp, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		SceneStack.setCurrentScene(resultScene);
		resultScene.getStylesheets().add(ResultScene.class.getClassLoader().getResource("table.css").toString());

		primaryStage.setScene(resultScene);
		primaryStage.show();
		
		table.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Author>() {
            public void onChanged(ListChangeListener.Change<? extends Author> c) {
            	Author author=null;
                for (Author p : c.getList()) {
                  author=p;
                  
                }
             SceneStack.pushSceneToStack(resultScene);
             try {
				AuthorScene.displayAuthorDisplayScene(author,primaryStage);
             } catch (SQLException e) {
				// TODO Auto-generated catch block
				AlertUtil.displayAlert("Error", "Oops, you got soemthing wrong!", 
						ERROR_RETRIEVING_AUTHOR);
                }
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
