package io.simplon.toomanychoco.model;

public class User {

	private String username;
	private String firstName;

	public User(String username, String firstName) {
		this.username = username;
		this.firstName = firstName;
	}

	public User() {
		
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
}
