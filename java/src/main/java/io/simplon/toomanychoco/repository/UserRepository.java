package io.simplon.toomanychoco.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import io.simplon.toomanychoco.db.DbConnector;
import io.simplon.toomanychoco.model.User;

public class UserRepository {

	private static final UserRepository instance = new UserRepository();

	public static UserRepository getInstance() {
		return instance;
	}

	private static final String SQL_FIND_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
	private static final String SQL_CREATE_USER = "INSERT INTO users (username,firstname,email,password) VALUES (?,?,?,?)";


	private Connection connection = null;

	private UserRepository() {
		try {
			connection = DbConnector.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<User> findByUsername(String search) {
		try (
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USERNAME);) {
			statement.setString(1, search);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String username = resultSet.getString("username");
				String firstName = resultSet.getString("first_name");
				return Optional.of(new User(username, firstName));
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	public void createUser(User user){
		try(
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER);){
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.geteMail());
			statement.setString(4, user.getPassword());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
