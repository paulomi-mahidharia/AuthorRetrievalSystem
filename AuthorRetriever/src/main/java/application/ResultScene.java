package application;

import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.PROGRESS_COLOR;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.PROGRESS_INDICATOR_DIMENSION;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.RESULT;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;
import static com.neu.msd.AuthorRetriever.constants.ValidationConstants.ERROR_RETRIEVING_AUTHOR;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.AUTHORNAME;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.URL;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.UNIVERSITY;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SERIALNO;

import static com.neu.msd.AuthorRetriever.util.HandCursor.showHandCursor;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.AuthorPaper;
import com.neu.msd.AuthorRetriever.model.Conference;
import com.neu.msd.AuthorRetriever.service.AuthorInfoService;
import com.neu.msd.AuthorRetriever.service.AuthorInfoServiceImpl;
import com.neu.msd.AuthorRetriever.service.ExportResult;
import com.neu.msd.AuthorRetriever.service.ExportResultPdfImpl;
import com.neu.msd.AuthorRetriever.util.AlertUtil;
import com.neu.msd.AuthorRetriever.util.NavigationBar;
import com.neu.msd.AuthorRetriever.util.SceneStack;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 *  The below class is used to display the list of author according to the search criteria that is specified by user
 *  The data is displayed on result page where the page displays list of authors with author information
 *  @Given A list of authors and a stage to display the scene
 *  @return Returns a  javaFX scene that displays the result page.
 */

@SuppressWarnings({ "rawtypes", "restriction", "unchecked" })
public class ResultScene {
	
	private static TableView table = null;
	private final static int rowsPerPage = 20;
	private static List<Author>authorList=null;
	private static Scene resultScene = null;
	
	private static StackPane stackPane = null;
	
	public static void displayResultScene(List<Author> resultedAuthors,Stage primaryStage){
		
		stackPane = new StackPane();
		table = new TableView(); 
		System.out.println("RESULT ::: "+resultedAuthors.size());
		authorList = resultedAuthors;
        
		table.setEditable(true);
		ObservableList<Author> authorData = FXCollections.observableArrayList(authorList);
        TableColumn<Author,Number> srNo = new TableColumn(SERIALNO);
        TableColumn author = new TableColumn(AUTHORNAME);
        
        PropertyValueFactory<Author,Hyperlink> rmbutton = new PropertyValueFactory<Author,Hyperlink>("name");
        author.setCellValueFactory(rmbutton);
        
        srNo.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
      
        TableColumn authorColumn = new TableColumn(UNIVERSITY);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("affiliation"));
        
        TableColumn authorUrlColumn = new TableColumn(URL);
        authorUrlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        
        table.getColumns().addAll(srNo, author, authorColumn, authorUrlColumn);
        showHandCursor(table);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        Button btnBackToSearch = new Button("Search Page");
        showHandCursor(btnBackToSearch);
        btnBackToSearch.setStyle("-fx-border-color: #b22222");
       
		btnBackToSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
    			primaryStage.setScene(SceneStack.getSceneAtTopOfStack());
    			primaryStage.show();
			}	
		});

		
	    Button buttonExportPdf = new Button("Export PDF");
	    showHandCursor(buttonExportPdf);
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

		
		resultScene = new Scene(stackPane, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		
		resultScene.getStylesheets().add(ResultScene.class.getClassLoader().getResource("table.css").toString());

		primaryStage.setScene(resultScene);
		
		
		table.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Author>() {
            public void onChanged(ListChangeListener.Change<? extends Author> c) {
            	Author author=null;
                for (Author p : c.getList()) {
                  author=p;
                }
               
                loadAuthorInformation(author, primaryStage, bp);

            }
        });
		table.getSelectionModel().clearSelection();
		stackPane.getChildren().add(bp);
		
		SceneStack.setCurrentScene(resultScene);
		 
		primaryStage.show();
	}
	
	
	private static void loadAuthorInformation(Author author, Stage primaryStage, BorderPane bp){
		ProgressIndicator indicator = new ProgressIndicator();
		indicator.setStyle(PROGRESS_COLOR);
		indicator.setMinSize(PROGRESS_INDICATOR_DIMENSION, PROGRESS_INDICATOR_DIMENSION);

		VBox loadingPane = new VBox();
		loadingPane.getChildren().addAll(indicator);
		loadingPane.setAlignment(Pos.CENTER);
		stackPane.getChildren().add(loadingPane);
		
		Task<Void> longTask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				
				SceneStack.pushSceneToStack(resultScene);
	             try {
	            	AuthorInfoService authorInfoService=new AuthorInfoServiceImpl();
	         		List<AuthorPaper> paperInfo=authorInfoService.getAuthorPapers(author.getAuthorId());
	         		List<Conference>conferences=authorInfoService.getAuthorConferenceServed(author.getAuthorId());
					
	         		author.setPapers(paperInfo);
	         		author.setConferences(conferences);
	             } catch (SQLException e) {
	            	loadingPane.setVisible(false);
		    		bp.setDisable(false);
					AlertUtil.displayAlert("Error", "Oops, you got soemthing wrong!", 
							ERROR_RETRIEVING_AUTHOR);
	             }
	             
	             return null;
            }
		};

		longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				loadingPane.setVisible(false);
	    		bp.setDisable(false);
	    		AuthorScene.displayAuthorDisplayScene(author,primaryStage);
            }
        });
	    
	    indicator.progressProperty().bind(longTask.progressProperty());

	    loadingPane.setVisible(true);
	    bp.setDisable(true);
        
        new Thread(longTask).start();
	}

	/**
	 * Create a pagination object which divides the table into pages according to the number of records.
	 *@returns a pagination object which is inserted in border panel
	 */
	private Pagination paginate(){
		
		Pagination pagination = new Pagination((authorList.size() / rowsPerPage + 1), 0);
		pagination.setPageFactory(this::createPage);
		return pagination;
	}


/**
 * Create a page with given index and return a object that can be added to pagination factory
 *
 */
    private Node createPage(int pageIndex) {

	   int fromIndex = pageIndex * rowsPerPage;
	   int toIndex = Math.min(fromIndex + rowsPerPage,authorList.size());
	   table.setItems(FXCollections.observableArrayList(authorList.subList(fromIndex, toIndex)));
	   return new BorderPane(table);
    }
}
