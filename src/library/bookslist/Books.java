package library.bookslist;


public class Books {

	int id,quantity;
	float price;
	String author,title;
	
	public Books(int id, String title,String author, int quantity, float price) {

		this.id = id;
		this.author = author;

		this.title = title;
		this.quantity = quantity;
		this.price = price;
	
		
	}
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
 
}