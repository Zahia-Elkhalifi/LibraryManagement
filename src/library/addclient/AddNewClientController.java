package library.addclient;



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




public class AddNewClientController {
	 @FXML
	    private JFXTextField textFirstName;

	    @FXML
	    private JFXTextField textLastName;

	    @FXML
	    private JFXTextField textAddress;

	    @FXML
	    private JFXTextField textPhone;

    @FXML
    private JFXButton closeButton;


    @FXML
	
	private void closeButtonAction(){
	    
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	   
	    stage.close();
	}
    Connection conn=null;
    PreparedStatement pst=null;
    @FXML
    private void addClient() {
    	conn=MysqlConnection.connectDb();
    	String sql="insert into clients(clientFirstName,clientLastName,clientAddress,clientPhone)values(?,?,?,?)";
    	try {
    		pst=conn.prepareStatement(sql);
    		pst.setString(1, textFirstName.getText());
    		pst.setString(2, textLastName.getText());
    		pst.setString(3, textAddress.getText());
    		pst.setString(4, textPhone.getText());
    		pst.execute();
    		Alert message = new Alert(Alert.AlertType.INFORMATION);
    		message.setContentText("client added");
    		message.setGraphic(null);
    		message.setHeaderText(null);
    		message.show();
    		closeButtonAction();
    		Main.showClientsListScene();
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

