package application;

import java.io.File;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.service.ExportResult;
import com.neu.msd.AuthorRetriever.service.ExportResultPdfImpl;
import com.neu.msd.AuthorRetriever.util.NavigationBar;
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

import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;

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
		
		TableColumn editColumn = new TableColumn("AuthorInformation");
        editColumn.setCellValueFactory(new PropertyValueFactory<Author,Hyperlink>("authorKey"));
		table.setEditable(false);
		ObservableList<Author> authorData = FXCollections.observableArrayList(authorList);
        TableColumn srNo = new TableColumn("Sr. No.");
        TableColumn author = new TableColumn("Author");
       
        PropertyValueFactory<Author,Hyperlink> rmbutton = new PropertyValueFactory<Author,Hyperlink>("name");
        author.setCellValueFactory(rmbutton);
        srNo.setCellValueFactory(new PropertyValueFactory<>("authorId"));
       
        table.getColumns().addAll(srNo, author, editColumn);
        
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(5);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(90);
        ColumnConstraints col3Constraints = new ColumnConstraints();
        col3Constraints.setPercentWidth(5);
		
	    Button buttonExportPdf = new Button("Export PDF");
	   
		HBox hbbuttonExportPdf= new HBox(20);
		hbbuttonExportPdf.setAlignment(Pos.BOTTOM_CENTER);
		hbbuttonExportPdf.getChildren().add(buttonExportPdf);
		
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
		HBox headerPane = NavigationBar.getHeaderPane(RESULT, primaryStage);
		headerPane.setPadding(new Insets(0, 0, 15, 0));
		headerPane.setSpacing(375);
		bp.setTop(headerPane);
		bp.setBottom(buttonExportPdf);
		ResultScene resultScenePaginate=new ResultScene();
		
		bp.setCenter(resultScenePaginate.paginate());
		resultScene = new Scene(bp, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
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
