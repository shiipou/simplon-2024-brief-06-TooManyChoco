package io.simplon.toomanychoco.repository;

import io.simplon.toomanychoco.db.DbConnector;
import io.simplon.toomanychoco.model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EventRespository {
    private static final EventRespository instance = new EventRespository();

    public static EventRespository getInstance() {
        return instance;
    }

    // requete qui récupère l'event en base de données
    private static final String SQL_FIND_EVENT = "SELECT e.event_date, e.event_id , u.first_name , p.pastry_name FROM event  e JOIN users u ON e.creator = username JOIN event_pastry ep ON e.event_id = ep.event_id JOIN pastry p ON ep.pastry_id = p.pastry_id WHERE e.event_date = ?";

    private Connection connection = null;

    private EventRespository() {
        try {
            connection = DbConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Event> findByEventDate(String date) {
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_EVENT);) {
            statement.setDate(1, java.sql.Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            // si résultat création d'un nouvel objet de class event
            if (resultSet.next()) {
                String event_date = resultSet.getString("event_date");
                int event_id = resultSet.getInt("event_id");
                String creator = resultSet.getString("first_name");
                List<String> pastry_list = new ArrayList<>();

                do {
                    String pastry_name = resultSet.getString("pastry_name");
                    pastry_list.add(pastry_name);
                } while (resultSet.next());

                return Optional.of(new Event(event_date, event_id, creator, pastry_list));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
