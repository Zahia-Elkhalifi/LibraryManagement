

package library.addbook;



import java.sql.Connection;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import library.Main;
import library.database.connection.MysqlConnection;


public class AddNewBookController {
	   @FXML
	    private JFXTextField textBookTitle;

	    @FXML
	    private JFXTextField textBookAuthor;

	    @FXML
	    private JFXTextField textBookQuantity;

	    @FXML
	    private JFXTextField textBookPrice;

    @FXML
    private  JFXButton closeButton;


    @FXML
	
	private  void closeButtonAction(){
	    
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	   
	    stage.close();
	}
    Connection conn=null;
    PreparedStatement pst=null;
    @FXML
    private void addBook() {
    	conn=MysqlConnection.connectDb();
    	String sql="insert into books(bookTitle,bookAuthor,bookQuantity,bookPrice)values(?,?,?,?)";
    	try {
    		pst=conn.prepareStatement(sql);
    		pst.setString(1, textBookTitle.getText());
    		pst.setString(2, textBookAuthor.getText());
    		pst.setString(3, textBookQuantity.getText());
    		pst.setString(4, textBookPrice.getText());
    		
    		pst.execute();
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
    		message.setContentText("book added");
    		message.setGraphic(null);
    		message.setHeaderText(null);
    		message.show();
    		closeButtonAction();
    		Main.showBooksListScene();
    	}
    	catch(Exception e) {
    		
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
    		message.setContentText(e.toString());
    		message.setGraphic(null);
    		message.setHeaderText(null);
    		message.show();    	}
    	
    	
    }
    
    
    
    
    
    
    
	}
