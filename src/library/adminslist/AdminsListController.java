package library.adminslist;



import java.io.IOException;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import library.Main;
import library.database.connection.MysqlConnection;



public class AdminsListController implements Initializable {
	
	 @FXML
	public void goHome() throws IOException  {
		
		
		Main.showHomeScene();
	
	}
	 @FXML
	public void goAddNewBook() throws IOException  {
		
	
		Main.showAddNewBook();
	
	}
	
	 
	
	 @FXML
	    private TableView<Admins> adminsTable;

	    @FXML
	    private TableColumn<Admins, Integer> adminId;

	    @FXML
	    private TableColumn<Admins, String> adminUserName;

	    @FXML
	    private TableColumn<Admins, String> adminPassword;

    
    @FXML
    private Label textBookId;
    @FXML
    ObservableList<Admins> listM;
    @FXML
    ObservableList<Admins> data;
    @FXML
    private TextField search;

    @FXML
    private JFXTextField textAdminUserName;

    @FXML
    private JFXTextField textAdminPassword;

   

    Connection conn=null;
  
    PreparedStatement pst=null;
    @FXML
    private Admins admin;
    @FXML
    public void getSelected() {
    	try{admin= adminsTable.getSelectionModel().getSelectedItem();
    	
    	textAdminUserName.setText(admin.getAdminUserName());
    	textAdminPassword.setText(admin.getAdminPassword());
    	}
    	catch(Exception e) {
    		JOptionPane.showMessageDialog(null,e);
    			}
    }
    public void Edit () {
    	try {
    	conn=MysqlConnection.connectDb();
    	String value1=textAdminUserName.getText();
    	String value2=textAdminPassword.getText();
    	
    	
    	String value5=Integer.toString(admin.getId());
    	String sql="update admins set adminUserName= '"+value1+"',aminPassword= '"+value2+"' where adminId='"+value5+"' ";
    	pst=conn.prepareStatement(sql) ;
    	pst.execute();
    	JOptionPane.showMessageDialog(null,"updated");
    	Main.showAdminsListScene();
    	
    	}
catch(Exception e) {
	JOptionPane.showMessageDialog(null,e);
		}
    	
    }
    @FXML
    public void Delete() {
    	
    	conn=MysqlConnection.connectDb();
    	String sql="delete from books where bookId=?";
    	
    	
    	
  
    	
    	
    	try {
    		pst=conn.prepareStatement(sql);
    		pst.setString(1,Integer.toString(admin.getId()));
    		pst.execute();
    		JOptionPane.showMessageDialog(null,"deleted");
    		Main.showBooksListScene();
    	}
    	
    	catch(Exception e) {
    		JOptionPane.showMessageDialog(null,e);
    	}
    	
    	
    	
    	
    }
  

   
    public static ObservableList<Admins> getDataBooks(){
		Connection conn =MysqlConnection.connectDb();
		ObservableList<Admins> list=FXCollections.observableArrayList();
		try {
			PreparedStatement ps =conn.prepareStatement("select * from admins");
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				
				list.add(new Admins(Integer.parseInt(rs.getString("adminId")), rs.getString("adminUserName"), rs.getString("adminPassword")));
			}
			
		}
		catch(Exception e) {
			
		}
		return list;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		adminId.setCellValueFactory(new PropertyValueFactory<Admins,Integer>("id"));
		adminUserName.setCellValueFactory(new PropertyValueFactory<Admins,String>("adminUserName"));
		adminPassword.setCellValueFactory(new PropertyValueFactory<Admins,String>("adminPassword"));
		
		 listM=getDataBooks();
		 adminsTable.setItems(listM);
		 
	}
    

	
	
	
	
	
	
	
	
	
	
	
	
}
