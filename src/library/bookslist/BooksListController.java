package library.bookslist;

import java.io.IOException;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import library.Main;
import library.database.connection.MysqlConnection;



public class BooksListController implements Initializable {
	
	 @FXML
	public void goHome() throws IOException  {
		
		
		Main.showHomeScene();
	
	}
	 @FXML
	public void goAddNewBook() throws IOException  {
		
	
		Main.showAddNewBook();
	
	}
	
	 
	
    @FXML
    private TableView<Books> booksTable;

    @FXML
    private TableColumn<Books, Integer> bookId;

    @FXML
    private TableColumn<Books, String> bookTitle;

    @FXML
    private TableColumn<Books, String> bookAuthor;

    @FXML
    private TableColumn<Books, Integer> bookQuantity;

    @FXML
    private  TableColumn<Books, Float> bookPrice;
    
    @FXML
    private Label textBookId;
    @FXML
    ObservableList<Books> listM;
    
    @FXML
    private TextField search;

    @FXML
    private JFXTextField textBookAuthor;

    @FXML
    private JFXTextField textBookTitle;

    @FXML
    private JFXTextField textBookQuantity;

    @FXML
    private JFXTextField textBookPrice;
    
   

    Connection conn=null;
  
    PreparedStatement pst=null;
    @FXML
    private Books book;
    @FXML
    public void getSelected() {
    	try{book=booksTable.getSelectionModel().getSelectedItem();
    	
    	textBookAuthor.setText(book.getAuthor());
    	textBookTitle.setText(book.getTitle());
    	textBookQuantity.setText(Integer.toString(book.getQuantity()));
    	textBookPrice.setText(Float.toString(book.getPrice()));}
    	catch(NullPointerException e) {
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText("You selected a blank row!");
       		message.setGraphic(null);
       		message.setHeaderText(null);
       		message.show(); 
       		
		}
    	catch(Exception e) {
    		
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText(e.toString());
       		message.setGraphic(null);
       		message.setHeaderText(null);
       		message.show(); 
       		
    			}
    }
    public void Edit () {
    	try {
    	conn=MysqlConnection.connectDb();
    	String value1=textBookTitle.getText();
    	String value2=textBookAuthor.getText();
    	String value3=textBookQuantity.getText();
    	String value4=textBookPrice.getText();
    	
    	String value5=Integer.toString(book.getId());
    	String sql="update books set bookTitle= '"+value1+"',bookAuthor= '"+value2+"',bookQuantity= '"+value3+"',bookPrice= '"+value4+"' where bookId='"+value5+"' ";
    	pst=conn.prepareStatement(sql) ;
    	pst.execute();
    	Alert message = new Alert(Alert.AlertType.INFORMATION);
   		message.setContentText("updated");
   		message.setGraphic(null);
   		message.setHeaderText(null);
   		message.show(); 
    	Main.showBooksListScene();
    	
    	}
catch(Exception e) {
	Alert message = new Alert(Alert.AlertType.INFORMATION);
		message.setContentText(e.toString());
		message.setGraphic(null);
		message.setHeaderText(null);
		message.show(); 
		}
    	
    }
    @FXML
    public void confirm() {
    	
    }
    @FXML
    public void Delete() {
    	
    	conn=MysqlConnection.connectDb();
    	String sql="delete from books where bookId=?";
    	
    	
    	
  
    	
    	
    	try {
    		pst=conn.prepareStatement(sql);
    		pst.setString(1,Integer.toString(book.getId()));
    		pst.execute();
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText("deleted");
       		message.setGraphic(null);
       		message.setHeaderText(null);
       		message.show(); 
    		Main.showBooksListScene();
    	}
    	catch(NullPointerException e) {
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText("You selected a blank row!");
       		message.setGraphic(null);
       		message.setHeaderText(null);
       		message.show(); 
       		
		}
    	catch(SQLIntegrityConstraintViolationException e) {
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText("You can't delete this item from the list");
       		message.setGraphic(null);
       		message.setHeaderText(null);
       		message.show(); 
    	}
    	
    	
    	catch(Exception e) {
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText(e.toString());
       		message.setGraphic(null);
       		message.setHeaderText(null);
       		message.show(); 
    	}
    	
    	
    	
    	
    }
  

   
    public static ObservableList<Books> getDataBooks(){
		Connection conn =MysqlConnection.connectDb();
		ObservableList<Books> list=FXCollections.observableArrayList();
		try {
			PreparedStatement ps =conn.prepareStatement("select * from books");
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				
				list.add(new Books(Integer.parseInt(rs.getString("bookId")), rs.getString("bookTitle"), rs.getString("bookAuthor"), Integer.parseInt(rs.getString("bookQuantity")),Float.parseFloat(rs.getString("bookPrice"))));
			}
			
		}
		catch(Exception e) {
			Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText(e.toString());
       		message.setGraphic(null);
       		message.setHeaderText(null);
       		message.show(); 
		}
		return list;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bookId.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
		bookTitle.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
		bookAuthor.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
		bookQuantity.setCellValueFactory(new PropertyValueFactory<Books,Integer>("quantity"));
		bookPrice.setCellValueFactory(new PropertyValueFactory<Books,Float>("price"));
		 listM=getDataBooks();
		 booksTable.setItems(listM);
		 initFilter();
		 
	}
    
@FXML
 private void initFilter() {

        

        search.textProperty().addListener(new InvalidationListener() {


            @Override

            public void invalidated(Observable o) {

                if(search.textProperty().get().isEmpty()) {

                    booksTable.setItems(listM);

                    return;

                }

                ObservableList<Books> tableItems = FXCollections.observableArrayList();

                ObservableList<TableColumn<Books, ?>> cols = booksTable.getColumns();

                for(int i=0; i<listM.size(); i++) {

                    

                    for(int j=0; j<cols.size(); j++) {

                        TableColumn col = cols.get(j);

                        String cellValue = col.getCellData(listM.get(i)).toString();

                        cellValue = cellValue.toLowerCase();

                        if(cellValue.contains(search.textProperty().get().toLowerCase())) {

                            tableItems.add(listM.get(i));

                            break;

                        }                        

                    }


                }

                booksTable.setItems(tableItems);

            }

			

        });

    }
	
	
	
	
	
	
	
	
	
	
	
}
