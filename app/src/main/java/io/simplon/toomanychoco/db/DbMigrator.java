package io.simplon.toomanychoco.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbMigrator {
    private static final String MIGRATION_SCRIPTS_DIR = "src/main/resources/";
    private static final String MIGRATION_TABLE_NAME = "migrations";

    private Connection connection;

    public DbMigrator(Connection connection) {
        this.connection = connection;
    }

    public void migrateDatabase() throws SQLException, IOException {
        boolean previousCommitState = connection.getAutoCommit();
        connection.setAutoCommit(false);
        // Create migration table if not exists
        createMigrationTable();

        // Get list of migration scripts from resources directory
        List<Path> migrationScripts = getMigrationScripts();

        boolean hasNewMigrations = false;
        // Execute each migration script if not already executed
        for (Path script : migrationScripts) {
            String filename = script.getFileName().toString();

            if (!isScriptExecuted(connection, filename)) {
                if (!hasNewMigrations) {
                    System.out.println("Database migration needed. Running database upgrade...");
                }

                hasNewMigrations = true;
                executeMigrationScript(connection, script);
                markScriptAsExecuted(connection, filename);
            }
        }

        connection.commit();
        connection.setAutoCommit(previousCommitState);

        if (migrationScripts.size() > 0) {
            if (hasNewMigrations)
                System.out.println("Database migration completed successfully.");
            else
                System.out.println("No Database migration needed.");
        }
    }

    private void createMigrationTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + MIGRATION_TABLE_NAME + " ("
                    + "filename VARCHAR(255) PRIMARY KEY,"
                    + "executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")");
        }
    }

    private List<Path> getMigrationScripts() throws IOException {
        List<Path> scripts = new ArrayList<>();
        try {
            Files.walk(Paths.get(MIGRATION_SCRIPTS_DIR))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".sql"))
                    .sorted()
                    .forEach(scripts::add);
        } catch (IOException e) {
            throw new IOException("Error reading migration scripts from directory", e);
        }
        return scripts;
    }

    private boolean isScriptExecuted(Connection connection, String filename) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT COUNT(*) FROM " + MIGRATION_TABLE_NAME + " WHERE filename = ?")) {
            statement.setString(1, filename);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        }
    }

    private void executeMigrationScript(Connection connection, Path scriptPath) throws SQLException, IOException {
        try {
            String scriptContent = new String(Files.readAllBytes(scriptPath));
            try (Statement statement = connection.createStatement()) {
                statement.execute(scriptContent);
            }
        } catch (SQLException | IOException e) {
            connection.rollback();
            throw new SQLException("Error executing migration script: " + scriptPath, e);
        }
    }

    private void markScriptAsExecuted(Connection connection, String filename) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + MIGRATION_TABLE_NAME + " (filename) VALUES (?)")) {
            statement.setString(1, filename);
            statement.executeUpdate();
        }
    }
}
