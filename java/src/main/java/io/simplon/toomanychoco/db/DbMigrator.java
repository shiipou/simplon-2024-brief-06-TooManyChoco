package io.simplon.toomanychoco.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class DbMigrator {
    private static final String MIGRATION_SCRIPTS_DIR = "migrations";
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
        Map<String, String> migrationScripts = getMigrationScripts();

        boolean hasNewMigrations = false;
        // Execute each migration script if not already executed
        for (Entry<String, String> script : migrationScripts.entrySet()) {
            String filename = script.getKey();

            if (!isScriptExecuted(connection, filename)) {
                if (!hasNewMigrations) {
                    System.out.println("Database migration needed. Running database upgrade...");
                }

                hasNewMigrations = true;
                executeMigrationScript(connection, script.getKey(), script.getValue());
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

    private Map<String, String> getMigrationScripts() throws IOException {
        Map<String, String> scripts = new HashMap<>();
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL migrationScriptsDir = classLoader.getResource(MIGRATION_SCRIPTS_DIR);
        if (migrationScriptsDir.getProtocol().equals("jar")) {
            String jarPath = migrationScriptsDir.getPath().substring(5, migrationScriptsDir.getPath().indexOf("!"));
            JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
            jar.stream()
                .filter(file -> {
                    String[] paths = file.getName().split("/");
                    return paths.length > 1 && paths[0].equals(MIGRATION_SCRIPTS_DIR) && paths[paths.length - 1].endsWith(".sql");
                    })
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                .forEach(script -> {
                    String scriptName = script.getName();
                    StringBuilder scriptContent = new StringBuilder();
                    InputStream scriptContentInputStream = classLoader.getResourceAsStream(script.getName());
                    try (InputStreamReader streamReader =
                        new InputStreamReader(scriptContentInputStream, StandardCharsets.UTF_8);
                        BufferedReader reader = new BufferedReader(streamReader)) {

                        String line;
                        while ((line = reader.readLine()) != null) {
                            scriptContent.append(line).append("\n");
                        }
                        scripts.put(scriptName, scriptContent.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        } else {
            try {
                Files.walk(Paths.get("src/main/resources/"+ MIGRATION_SCRIPTS_DIR))
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".sql"))
                        .sorted()
                        .forEach(path -> {
                            String scriptName = path.toString();
                            StringBuilder scriptContent = new StringBuilder();

                            try (FileReader scriptContentInputStream = new FileReader(scriptName)) {
                                BufferedReader reader = new BufferedReader(scriptContentInputStream);

                                String line;
                                while ((line = reader.readLine()) != null) {
                                    scriptContent.append(line).append("\n");
                                }
                                scripts.put(scriptName, scriptContent.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            } catch (IOException e) {
                throw new IOException("Error reading migration scripts from directory", e);
            }
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

    private void executeMigrationScript(Connection connection, String scriptPath, String scriptContent) throws SQLException, IOException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(scriptContent);
        } catch (SQLException e) {
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
