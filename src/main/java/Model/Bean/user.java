package Model.Bean;

public class user {
	int uid;
	String username;
	String password;
	String fullname;
	String email;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public user(int uid, String username, String password, String fullname, String email) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
	}
	public user() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}