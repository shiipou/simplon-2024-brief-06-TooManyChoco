package io.simplon.toomanychoco.model;

public class User {

	private String username;
	private String firstname;
	private String email;
	private String password;

	// constructeur vide par défaut à conserver car sans json parser ne fonctionnera pas
	public User(){

	}
	public User(String username, String firstname, String email, String password) {
		this.username = username;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
	}

	public User(String username, String firstname) {
		this.username = username;
		this.firstname = firstname;	
	}
	public User(String username) {
		this.username = username;
	}

	// public User(String email, String password) {
	// 	this.email = email;
	// 	this.password = password;
	// }

	public User(String username, String firstname, String email, String password) {
		this.username = username;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
	}

	public User(String username, String firstname) {
		this.username = username;
		this.firstname = firstname;	
	}
	public User(String username) {
		this.username = username;
	}

	// public User(String email, String password) {
	// 	this.email = email;
	// 	this.password = password;
	// }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", firstName='" + firstname + '\'' +
				", eMail='" + email + '\'' +
				'}';
	}
}
