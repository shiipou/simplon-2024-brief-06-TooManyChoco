package io.simplon.toomanychoco.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.simplon.toomanychoco.db.DbConnector;
import io.simplon.toomanychoco.model.Viennoiserie;

public class ViennoiserieRepository {

    private static final ViennoiserieRepository instance = new ViennoiserieRepository();

    public static ViennoiserieRepository getInstance() {
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------------

    private static final String SQL_FIND_BY_NAME = "SELECT * FROM viennoiserie WHERE nom = ?";

    private static final String SQL_FIND_ALL = "SELECT * FROM viennoiserie";

    // ---------------------------------------------------------------------------------------------------------

    private Connection connection = null;

    private ViennoiserieRepository() {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------------------

    public Optional<Viennoiserie> findByName(String search) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME);) {
            statement.setString(1, search);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                return Optional.of(new Viennoiserie(nom));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // ---------------------------------------------------------------------------------------------------------

    public List<Viennoiserie> findAll() {
        List<Viennoiserie> viennoiseries = new ArrayList<>();

         try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);) {
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String nom = resultSet.getString("nom");
                viennoiseries.add(new Viennoiserie(nom));
            }                
        
        } catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

        return viennoiseries;
    }

    // ---------------------------------------------------------------------------------------------------------

}
