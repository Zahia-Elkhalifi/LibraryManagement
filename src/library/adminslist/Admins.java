package library.adminslist;

public class Admins {
	int id;

	String adminUserName,adminPassword;
	/**
	 * @param id
	 * @param adminUserName
	 * @param adminPassword
	 */
	public Admins(int id, String adminUserName, String adminPassword) {
		
		this.id = id;
		
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}



}
