package library.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import library.Main;
import library.database.connection.MysqlConnection;

public class LoginController {
	
	 @FXML
		public void goHome() throws IOException  {
			
			
			Main.showHomeScene();
		
		}
	
	@FXML
	private Label lblErrors;
	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtPassword;
	
	Connection conn=null;
    PreparedStatement pst=null;
	
    public void login(ActionEvent event) throws IOException{
    	conn=MysqlConnection.connectDb();
    	try{
    	PreparedStatement pst1 =conn.prepareStatement("select * from admins where adminId="+1);
    	
    	ResultSet rst1 =pst1.executeQuery();
    	while(rst1.next()) {
         String userName= rst1.getString("adminUserName");
       
		String password= rst1.getString("adminPassword");
		if(txtUserName.getText().equals(userName)&& txtPassword.getText().equals(password)) {
    		Main.showHomeScene();
    		
    		
    	}
    	else if (txtUserName.getText().equals(""))
       {
    		lblErrors.setText("Enter UserName");
    		
    }
    	  	else if (txtPassword.getText().equals(""))
    	       {
    	    		lblErrors.setText("Enter Password");
    	    		
    	    }
    	else {
    		lblErrors.setText("Login Failed, Retry");
    		
    }
		
		 
    	}}
    	
    	catch(Exception e) {
        	
    	}
    	
		
    
   
    
}
}