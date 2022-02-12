package library.clientslist;


import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import library.Main;
import library.bookslist.Books;
import library.database.connection.MysqlConnection;

public class ClientsListController implements Initializable {
	@FXML
	public void goHome() throws IOException  {
		
		
		Main.showHomeScene();
	
	}
	@FXML
	public void goAddNewClient() throws IOException  {
		
		
		Main.showAddNewClient();
	
	}

    @FXML
    private TableView<Clients> clientsTable;

    @FXML
    private TableColumn<Clients, Integer> clientId;

    @FXML
    private TableColumn<Clients, String> clientFirstName;

    @FXML
    private TableColumn<Clients, String> clientLastName;

    @FXML
    private TableColumn<Clients, String> clientAddress;

    @FXML
    private TableColumn<Clients, Integer> clientPhone;
    @FXML
    private JFXTextField textFirstName;

    @FXML
    private JFXTextField textLastName;

    @FXML
    private JFXTextField textAddress;

    @FXML
    private JFXTextField textPhone;

    
    ObservableList<Clients> listM;
    int index =-1;
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    @FXML
    private Clients client;
    @FXML
    private TextField search;
    @FXML
  
    public void getSelected() {
    	try{client=clientsTable.getSelectionModel().getSelectedItem();
    	
    	textFirstName.setText(client.getFirstName());
    	textLastName.setText(client.getLastName());
    	textAddress.setText(client.getAddress());
    	textPhone.setText(Integer.toString(client.getPhone()));}
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
    	String value1=textFirstName.getText();
    	String value2=textLastName.getText();
    	String value3=textAddress.getText();
    	String value4=textPhone.getText();
    	
    	String value5=Integer.toString(client.getId());
    	String sql="update clients set clientFirstName= '"+value1+"',clientLastName= '"+value2+"',clientAddress= '"+value3+"',clientPhone= '"+value4+"' where clientId='"+value5+"' ";
    	pst=conn.prepareStatement(sql) ;
    	pst.execute();
    	Alert message = new Alert(Alert.AlertType.INFORMATION);
   		message.setContentText("updated");
   		message.setGraphic(null);
   		message.setHeaderText(null);
   		message.show(); 
    	Main.showClientsListScene();;
    	
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
    public void Delete() {
    	
    	conn=MysqlConnection.connectDb();
    	String sql="delete from clients where clientId=?";
    	
    	
    	
  
    	
    	
    	try {
    		pst=conn.prepareStatement(sql);
    		pst.setString(1,Integer.toString(client.getId()));
    		pst.execute();
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText("deleted");
       		message.setGraphic(null);
       		message.setHeaderText(null);
       		message.show(); 
    		Main.showClientsListScene();
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
       		message.setContentText("You can't delete this client from the list");
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
    public static ObservableList<Clients> getDataBooks() {
		Connection conn = MysqlConnection.connectDb();
		ObservableList<Clients> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from clients");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				list.add(new Clients(Integer.parseInt(rs.getString("clientId")), rs.getString("clientFirstName"),
						rs.getString("clientLastName"),rs.getString("clientAddress"),
						Integer.parseInt(rs.getString("clientPhone"))));
			}
		} catch (Exception e) {

		}
		return list;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientId.setCellValueFactory(new PropertyValueFactory<Clients,Integer>("id"));
		 clientFirstName.setCellValueFactory(new PropertyValueFactory<Clients,String>("firstName"));
		 clientLastName.setCellValueFactory(new PropertyValueFactory<Clients,String>("lastName"));
		 clientAddress.setCellValueFactory(new PropertyValueFactory<Clients,String>("address"));
		 clientPhone.setCellValueFactory(new PropertyValueFactory<Clients,Integer>("phone"));
		 listM=getDataBooks();
		 clientsTable.setItems(listM);
		 initFilter();
	}
	@FXML
	 private void initFilter() {

	        

	        search.textProperty().addListener(new InvalidationListener() {


	            @Override

	            public void invalidated(Observable o) {

	                if(search.textProperty().get().isEmpty()) {

	                    clientsTable.setItems(listM);

	                    return;

	                }

	                ObservableList<Clients> tableItems = FXCollections.observableArrayList();

	                ObservableList<TableColumn<Clients, ?>> cols = clientsTable.getColumns();

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

	                clientsTable.setItems(tableItems);

	            }

				

	        });

	    }
		
    
}
