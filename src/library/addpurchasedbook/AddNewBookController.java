

package library.addpurchasedbook;



import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import library.Main;
import library.bookslist.BooksListController;
import library.database.connection.MysqlConnection;


public class AddNewBookController {
	  @FXML
	    private JFXTextField textClientId;

	    @FXML
	    private JFXTextField textBookId;

	    @FXML
	    private JFXTextField textQuantity;

	 

    @FXML
    private  JFXButton closeButton;


    @FXML
	
	private  void closeButtonAction(){
	    
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	   
	    stage.close();
	}
    Connection conn=null;
    PreparedStatement pst=null;
    PreparedStatement pst1=null;
    PreparedStatement pst2=null;
    @FXML
    private void addBook() {
    	conn=MysqlConnection.connectDb();
	    String id=textBookId.getText();
	    String sql="insert into purchasedbooks(clientId,bookId,quantity,price)values(?,?,?,?)";
    	 int firstQuantity=0;
    	 float bookPrice=0;
    	 int quantityWanted = 0;
    	 int newQuantity=0;
    		try { 
    			
    			PreparedStatement pst1 =conn.prepareStatement("select * from books where bookId="+id);
    			ResultSet rst1 =pst1.executeQuery();
    			 
    			   
   while(rst1.next()) {
	    firstQuantity= Integer.parseInt((rst1.getString("bookQuantity")));
	    bookPrice=Float.parseFloat(rst1.getString("bookPrice"));
		 quantityWanted= Integer.parseInt(textQuantity.getText());
		 newQuantity=firstQuantity-quantityWanted;
   }}
    		catch(Exception e) {
        		
        	}
    		try {
    			if(firstQuantity==0 ) {
    				 Alert message = new Alert(Alert.AlertType.INFORMATION);
    			   		message.setContentText("not available in stock!");
    			   		message.setGraphic(null);
    			   		message.setHeaderText(null);
    			   		message.show(); 
    			}
    			else if(quantityWanted==0) {
   				 Alert message = new Alert(Alert.AlertType.INFORMATION);
   			   		message.setContentText("you can't add null quantity");
   			   		message.setGraphic(null);
   			   		message.setHeaderText(null);
   			   		message.show(); 
   			}
    			else if(quantityWanted>firstQuantity) {
		   Alert message = new Alert(Alert.AlertType.INFORMATION);
   		message.setContentText("max quantity "+firstQuantity);
   		message.setGraphic(null);
   		message.setHeaderText(null);
   		message.show(); 
		   //JOptionPane.showMessageDialog(null,"max quantity "+firstQuantity);
		  
	   }
	  
	   else {
		   float pricequantity=bookPrice*quantityWanted;
		   pst=conn.prepareStatement(sql);
   		
   		pst.setString(1, textClientId.getText());
   		pst.setString(2, textBookId.getText());
   		pst.setString(3, textQuantity.getText());
	    pst.setString(4, Float.toString(pricequantity));
		pst.execute();
    		
    
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText("book added ");
       		message.setGraphic(null);
       		message.setHeaderText(null);
       		message.show(); 
    		closeButtonAction();
    		Main.showIssuedBooksScene();
    		String sql1="update books set bookQuantity= '"+newQuantity+"' where bookId='"+id+"'";
        	pst2=conn.prepareStatement(sql1) ;
        	pst2.execute();}
    		}
    		catch(Exception e) {
        		
        		Alert message = new Alert(Alert.AlertType.INFORMATION);
           		message.setContentText("make sure that the clientID or bookID are valid");
           		message.setGraphic(null);
           		message.setHeaderText(null);
           		message.show(); 
        	
        	
        }
    	}
    public void Delete() {
    	
    	conn=MysqlConnection.connectDb();
    	String sql="select from books where bookId=?";
    	try {
    		System.out.println(sql);
    		pst=conn.prepareStatement(sql);
    		pst.setString(1,textBookId.getText());
    		pst.execute();
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText("deleted ");
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
    
    
    
    
    
    
	}