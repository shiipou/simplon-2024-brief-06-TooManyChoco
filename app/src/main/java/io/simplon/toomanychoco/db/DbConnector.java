package io.simplon.toomanychoco.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/toomanychoco";
	private static final String JDBC_USERNAME = "postgres";
	private static final String JDBC_PASSWORD = "postgres";

	private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			DbConnector.connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
		}
		return connection;
	}

	private DbConnector(){}
}
