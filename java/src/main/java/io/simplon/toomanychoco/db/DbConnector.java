package io.simplon.toomanychoco.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class DbConnector {
	private static final String JDBC_URL = Optional.ofNullable(System.getenv("JDBC_URL")).orElse("jdbc:postgresql://localhost:5432/TooManyChoco");
	private static final String JDBC_USERNAME = Optional.ofNullable(System.getenv("JDBC_USERNAME")).orElse("postgres");
	private static final String JDBC_PASSWORD = Optional.ofNullable(System.getenv("JDBC_PASSWORD")).orElse("postgres");

	private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			DbConnector.connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
		}
		return connection;
	}

	private DbConnector(){}
}
