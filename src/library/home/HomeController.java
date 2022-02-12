package library.home;


import java.io.IOException;


import library.*;

public class HomeController {

	
	public void goLogin() throws IOException  {
		
	
		Main.showMainItems();
	
	}
	public void goBooksList() throws IOException  {
		
		
		Main.showBooksListScene();
	
	}
	public void goIssuedList() throws IOException  {
		
		
		Main.showIssuedBooksScene();
	
	}
public void goAdminsList() throws IOException  {
		
		
		Main.showAdminsListScene();
	
	}

public void goClientsList() throws IOException  {
	
	
	Main.showClientsListScene();

}

}
