package library.purchasedbooks;


public class PurchasedBooks {

	int clientId,bookId,quantity;
	float price;
	
	public PurchasedBooks(int clientId, int bookId,int quantity, float price) {

		this.clientId = clientId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.price = price;
	
		
	}
	 
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
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
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int clientId) {
		this.clientId = clientId;
	}
	
 
}