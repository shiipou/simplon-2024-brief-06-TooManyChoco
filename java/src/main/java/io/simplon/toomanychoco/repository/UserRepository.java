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

	private static final String SQL_FIND_IS_USER_EXIST = "SELECT * FROM users WHERE email = ? AND password = ?";

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
				String firstname = resultSet.getString("first_name");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				return Optional.of(new User(username, firstname, email, password));
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	public Optional<User> findIsUserExist(User user) {
		try (
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_IS_USER_EXIST);) {
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String username = resultSet.getString("username");
				String firstname = resultSet.getString("first_name");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				return Optional.of(new User(username, firstname, email, password));
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
