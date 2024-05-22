package io.simplon.toomanychoco.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import io.simplon.toomanychoco.db.DbConnector;
import io.simplon.toomanychoco.model.User;

// modèle Singleton (qu'une seule instance dans toute l'app)
public class UserRepository {

    private static final UserRepository instance = new UserRepository();

    // getInstance renvoie toujours la même instance de UserRepository
    public static UserRepository getInstance() {
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------------------

    private static final String SQL_FIND_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String SQL_CREATE_USER = "INSERT INTO users (username,first_name,email,password) VALUES (?,?,?,?)";

    // ---------------------------------------------------------------------------------------------------------

    private static final String SQL_FIND_IS_USER_EXIST = "SELECT * FROM users WHERE email = ? AND password = ?";

    private Connection connection = null;

    // constructeur privé : connexion à la BDD en utilisant
    // DbConnector.getConnection()
    private UserRepository() {
        try {
            connection = DbConnector.getConnection();
            // gestion d'erreur
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // recherche user par le username (prend un username en paramètre)
    public Optional<User> findByUsername(String search) {
        try (
                // prépare une requête SQL pour rechercher un user dans la table Users en
                // fonction du nom fourni
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USERNAME);) {
            statement.setString(1, search);
            ResultSet resultSet = statement.executeQuery();

            // Si user trouvé dans BDD, ses informations (username ett firstname) sont
            // extraites du résultat de la requête
            // et encapsulées dans un objet User (new User)
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

    public void createUser(User user) {
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER);) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
