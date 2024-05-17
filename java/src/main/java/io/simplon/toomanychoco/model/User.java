package io.simplon.toomanychoco.model;

public class User {


	private String username;
	private String firstName;
	private String eMail;
	private String password;

	public User() {

	}
	public User(String username, String firstName, String eMail, String password) {
		this.username = username;
		this.firstName = firstName;
		this.eMail = eMail;
		this.password = password;
	}

	public User(String username, String firstName) {
		this.username = username;
		this.firstName = firstName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
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
				", firstName='" + firstName + '\'' +
				", eMail='" + eMail + '\'' +
				'}';
	}
}
