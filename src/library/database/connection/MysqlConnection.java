package library.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
public class  MysqlConnection{
public Connection conn=null;

	public static Connection connectDb() {
	    String dbName="cslibrary";
		String userName="root";
		String password="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=(Connection)DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
			
			return conn;
		} catch (Exception e) {
			Alert message = new Alert(Alert.AlertType.INFORMATION);
       		message.setContentText(e.toString());
       		message.setGraphic(null);
       		message.setHeaderText(null);
       		message.show(); 
			
			return null;
		}
	}
	
	
}
