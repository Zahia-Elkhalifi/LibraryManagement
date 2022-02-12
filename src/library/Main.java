package library;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
private static Stage primaryStage;
private static  BorderPane mainLayout;
private static double xoffset=0;
private static double yoffset=0;
	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage=primaryStage;
		showMainView();
		showMainItems();
		
		
	}
	public void showMainView() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout=loader.load();
		Scene scene =new Scene(mainLayout);
		
		try {
			primaryStage.getIcons().add(new Image("/library/images/AAAAA1.png"));
			primaryStage.setScene(scene);
			
			
			
			 primaryStage.initStyle(StageStyle.UNDECORATED);
				mainLayout.setOnMousePressed(new EventHandler<MouseEvent>()
						{

							@Override
							public void handle(MouseEvent event) 
							{
							 xoffset = event.getSceneX();
							 yoffset = event.getSceneY();
							}
						}
						);
				mainLayout.setOnMouseDragged(new EventHandler<MouseEvent>()
				{

					@Override
					public void handle(MouseEvent event) 
					{
						primaryStage.setX(event.getScreenX() - xoffset);
						primaryStage.setY(event.getScreenY() - yoffset);

					}
				}
				);
				 primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		}
	public static void showMainItems() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Login.fxml"));
		BorderPane mainItems =loader.load();
		mainLayout.setCenter(mainItems);
		}
	public static   void showHomeScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("home/Home.fxml"));
		AnchorPane home = loader.load();
		mainLayout.setCenter(home);
		
	}
	public static   void showBooksListScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("bookslist/Books.fxml"));
		AnchorPane books = loader.load();
		mainLayout.setCenter(books);
		
	}
	public static   void showIssuedBooksScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("purchasedbooks/PurchasedBooks.fxml"));
		AnchorPane books = loader.load();
		mainLayout.setCenter(books);
}
	public static   void showAdminsListScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("adminslist/Admins.fxml"));
		AnchorPane admins = loader.load();
		mainLayout.setCenter(admins);
		
	}
	public static   void showClientsListScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("clientslist/Clients.fxml"));
		AnchorPane clients = loader.load();
		mainLayout.setCenter(clients);
		
	}

public  static void showAddNewBook() throws IOException {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(Main.class.getResource("addbook/AddNewBook.fxml"));
	
	AnchorPane addNewBook = loader.load();
	 Stage addDialogStage =new Stage();
	 
	 addDialogStage.initModality(Modality.WINDOW_MODAL);
	 addDialogStage.initOwner(primaryStage);
	 Scene scene =new Scene(addNewBook);
	try {
		addDialogStage.setScene(scene);
		
			
			
			 addDialogStage.initStyle(StageStyle.UNDECORATED);
				mainLayout.setOnMousePressed(new EventHandler<MouseEvent>()
						{

							@Override
							public void handle(MouseEvent event) 
							{
							 xoffset = event.getSceneX();
							 yoffset = event.getSceneY();
							}
						}
						);
				mainLayout.setOnMouseDragged(new EventHandler<MouseEvent>()
				{

					@Override
					public void handle(MouseEvent event) 
					{
						addDialogStage.setX(event.getScreenX() - xoffset);
						addDialogStage.setY(event.getScreenY() - yoffset);

					}
			
				}
				);
				addDialogStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	 
	 
}
public  static void showAddpurchaseddBook() throws IOException {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(Main.class.getResource("addpurchasedbook/AddNewBook.fxml"));
	AnchorPane addNewBook = loader.load();
	 Stage addDialogStage =new Stage();
	 
	 addDialogStage.initModality(Modality.WINDOW_MODAL);
	 addDialogStage.initOwner(primaryStage);
	 Scene scene =new Scene(addNewBook);
	try {
		addDialogStage.setScene(scene);
		
			
			
			 addDialogStage.initStyle(StageStyle.UNDECORATED);
				mainLayout.setOnMousePressed(new EventHandler<MouseEvent>()
						{

							@Override
							public void handle(MouseEvent event) 
							{
							 xoffset = event.getSceneX();
							 yoffset = event.getSceneY();
							}
						}
						);
				mainLayout.setOnMouseDragged(new EventHandler<MouseEvent>()
				{

					@Override
					public void handle(MouseEvent event) 
					{
						addDialogStage.setX(event.getScreenX() - xoffset);
						addDialogStage.setY(event.getScreenY() - yoffset);

					}
			
				}
				);
				addDialogStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	 
	 
}

public  static void showAddNewClient() throws IOException {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(Main.class.getResource("addclient/AddNewClient.fxml"));
	
	AnchorPane addNewBook = loader.load();
	 Stage addDialogStage =new Stage();
	 
	 addDialogStage.initModality(Modality.WINDOW_MODAL);
	 addDialogStage.initOwner(primaryStage);
	 Scene scene =new Scene(addNewBook);
	try {
		addDialogStage.setScene(scene);
		
			
			
			 addDialogStage.initStyle(StageStyle.UNDECORATED);
				mainLayout.setOnMousePressed(new EventHandler<MouseEvent>()
						{

							@Override
							public void handle(MouseEvent event) 
							{
							 xoffset = event.getSceneX();
							 yoffset = event.getSceneY();
							}
						}
						);
				mainLayout.setOnMouseDragged(new EventHandler<MouseEvent>()
				{

					@Override
					public void handle(MouseEvent event) 
					{
						addDialogStage.setX(event.getScreenX() - xoffset);
						addDialogStage.setY(event.getScreenY() - yoffset);

					}
			
				}
				);
				addDialogStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	 
	 
}
	
	public static void main(String[] args) {
		launch(args);
	}}