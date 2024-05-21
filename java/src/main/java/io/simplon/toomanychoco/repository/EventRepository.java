package io.simplon.toomanychoco.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.simplon.toomanychoco.db.DbConnector;
import io.simplon.toomanychoco.model.Event;
import io.simplon.toomanychoco.model.Pastry;
import io.simplon.toomanychoco.model.User;

public class EventRepository {

    private static final EventRepository instance = new EventRepository();

	public static EventRepository getInstance() {
		return instance;
	}

    // ---------------------------------------------------------------------------------------------------------

    private static final String SQL_FIND_ALL = "SELECT e.event_date, e.event_id , u.username , p.pastry_name FROM event e JOIN users u ON e.creator = username JOIN event_pastry ep ON e.event_id = ep.event_id JOIN pastry p ON ep.pastry_id = p.pastry_id ORDER BY e.event_date";
    /* private static final String SQL_POST_EVENT = "INSERT INTO event (event_date, creator, pastries) VALUES (?, ?)"; */
    

    // ---------------------------------------------------------------------------------------------------------


    private Connection connection = null;

    private EventRepository() {
		try {
			connection = DbConnector.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    // ---------------------------------------------------------------------------------------------------------
    
    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);) {
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Date event_date = resultSet.getDate("event_date");
                User creator = UserRepository.getInstance().findByUsername(resultSet.getString("creator")).get();
                Integer event_id = resultSet.getInt("event_id");
                List<Pastry> pastries = getPastryByEventId(event_id);
                events.add(new Event(event_id, event_date, creator, pastries ));
            }                
        
        } catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

        return events;
    }
    
    // ---------------------------------------------------------------------------------------------------------
    
    public void save(Event event, List<Pastry> pastries) throws SQLException {
        String insertEventSQL = "INSERT INTO event (event_date, creator) VALUES (?, ?) RETURNING event_id";
        String insertEventPastrySQL = "INSERT INTO event_pastry (event_id, pastry_id) VALUES (?, ?)";
    
        try (
            PreparedStatement statementEvent = connection.prepareStatement(insertEventSQL);
            PreparedStatement statementEventPastry = connection.prepareStatement(insertEventPastrySQL)
        ) {
            // Insert the event and retrieve its ID
            java.sql.Date formattedDate = java.sql.Date.valueOf(DateFormat.getDateInstance(DateFormat.SHORT, Locale.ROOT).format(event.getEvent_date()));
            statementEvent.setDate(1, formattedDate);
            statementEvent.setString(2, event.getCreator().getUsername());
            ResultSet resultSet = statementEvent.executeQuery();
            resultSet.next();
            int eventId = resultSet.getInt("event_id");
    
            // Insert event-pastry associations
            for (Pastry pastry : pastries) {
                int pastryId = getPastryIdByName(pastry.getPastry_name());
                statementEventPastry.setInt(1, eventId);
                statementEventPastry.setInt(2, pastryId);
                statementEventPastry.executeUpdate();
            }
        }
    }
    
    private int getPastryIdByName(String pastryName) throws SQLException {
        String selectPastryIdSQL = "SELECT pastry_id FROM pastry WHERE pastry_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectPastryIdSQL)) {
            statement.setString(1, pastryName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("pastry_id");
            } else {
                throw new SQLException("Pastry not found with name: " + pastryName);
            }
        }
    }

    private List<Pastry> getPastryByEventId(Integer event_id) throws SQLException {
        List<Pastry> pastries = new ArrayList<>();
        String SQL_GET_PASTRY_BY_EVENT_ID = "SELECT p.pastry_id , p.pastry_name FROM event_pastry ep JOIN pastry p ON p.pastry_id = ep.pastry_id WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_PASTRY_BY_EVENT_ID)) {
            statement.setInt(1, event_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pastries.add(new Pastry(resultSet.getString("pastry_name")));
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return pastries;
    }
    
    // ---------------------------------------------------------------------------------------------------------




}
