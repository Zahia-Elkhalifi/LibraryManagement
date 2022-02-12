package library.purchasedbooks;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import library.Main;
import library.bookslist.Books;
import library.database.connection.MysqlConnection;



public class PurchasedBooksController implements Initializable {
	
	 @FXML
	public void goHome() throws IOException  {
		
		
		Main.showHomeScene();
	
	}
	 @FXML
	public void goAddNewBook() throws IOException  {
		
	
		Main.showAddpurchaseddBook();
	
	}
	
	 
	
    @FXML
    private TableView<PurchasedBooks> purchasedBooksTabe;

    @FXML
    private TableColumn<PurchasedBooks, Integer> clientId;

    @FXML
    private TableColumn<PurchasedBooks, Integer> bookId;

    @FXML
    private TableColumn<PurchasedBooks, Integer> quantity;

    @FXML
    private TableColumn<PurchasedBooks, Float> price;

    @FXML
    private TextField search;
    
    @FXML
    ObservableList<PurchasedBooks> listM;
    
    Connection conn=null;
  
    PreparedStatement pst=null;
    
    
   
    public static ObservableList<PurchasedBooks> getDataBooks(){
		Connection conn =MysqlConnection.connectDb();
		ObservableList<PurchasedBooks> list=FXCollections.observableArrayList();
		try {
			PreparedStatement ps =conn.prepareStatement("select * from purchasedbooks");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				
				list.add(new PurchasedBooks(Integer.parseInt(rs.getString("clientId")),Integer.parseInt(rs.getString("bookId")), Integer.parseInt(rs.getString("quantity")), Float.parseFloat(rs.getString("price"))));
			}
		}
		catch(Exception e) {
			
		}
		return list;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientId.setCellValueFactory(new PropertyValueFactory<PurchasedBooks,Integer>("clientId"));
		bookId.setCellValueFactory(new PropertyValueFactory<PurchasedBooks,Integer>("bookId"));
		quantity.setCellValueFactory(new PropertyValueFactory<PurchasedBooks,Integer>("quantity"));
		price.setCellValueFactory(new PropertyValueFactory<PurchasedBooks,Float>("price"));
		 listM=getDataBooks();
		 purchasedBooksTabe.setItems(listM);
		 initFilter();
		 
	}
    
	
@FXML
 private void initFilter() {

        

        search.textProperty().addListener(new InvalidationListener() {


            @Override

            public void invalidated(Observable o) {

                if(search.textProperty().get().isEmpty()) {

                	purchasedBooksTabe.setItems(listM);

                    return;

                }

                ObservableList<PurchasedBooks> tableItems = FXCollections.observableArrayList();

                ObservableList<TableColumn<PurchasedBooks, ?>> cols = purchasedBooksTabe.getColumns();

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

                purchasedBooksTabe.setItems(tableItems);

            }

			

        });

    }}
    
