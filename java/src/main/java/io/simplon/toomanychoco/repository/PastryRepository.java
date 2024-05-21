package io.simplon.toomanychoco.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.simplon.toomanychoco.db.DbConnector;
import io.simplon.toomanychoco.model.Pastry;

public class PastryRepository {

    private static final PastryRepository instance = new PastryRepository();

    public static PastryRepository getInstance() {
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------------

    private static final String SQL_FIND_BY_NAME = "SELECT * FROM pastry WHERE pastry_name = ?";

    private static final String SQL_FIND_ALL = "SELECT * FROM pastry";

    // ---------------------------------------------------------------------------------------------------------

    private Connection connection = null;

    private PastryRepository() {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------------------

    public Optional<Pastry> findByName(String search) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME);) {
            statement.setString(1, search);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String pastry_name = resultSet.getString("pastry_name");
                return Optional.of(new Pastry(pastry_name));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // ---------------------------------------------------------------------------------------------------------

    public List<Pastry> findAll() {
        List<Pastry> pastries = new ArrayList<>();

         try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);) {
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String pastry_name = resultSet.getString("pastry_name");
                pastries.add(new Pastry(pastry_name));
            }                
        
        } catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

        return pastries;
    }

    // ---------------------------------------------------------------------------------------------------------

}
