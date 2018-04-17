package model;

public class User {

	private long id;
	private String username;
	private String password;
	private String email;
	private int genderId;
	
	public User(String username, String password, String email, int genderId) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.genderId = genderId;
	}

	public User(long id, String username, String password, String email, int genderId) {
		this(username, password, email, genderId);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public int getGenderId() {
		return genderId;
	}
	
	
	
	
}