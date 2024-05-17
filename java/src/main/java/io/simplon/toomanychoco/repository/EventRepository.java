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
import io.simplon.toomanychoco.model.User;

public class EventRepository {

    private static final EventRepository instance = new EventRepository();

	public static EventRepository getInstance() {
		return instance;
	}

    // ---------------------------------------------------------------------------------------------------------

    private static final String SQL_FIND_ALL = "SELECT * FROM event";
    private static final String SQL_POST_EVENT = "INSERT INTO event (\"event_date\", creator) VALUES (?, ?)";

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
                events.add(new Event(event_date, creator));
            }                
        
        } catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

        return events;
    }
    
    // ---------------------------------------------------------------------------------------------------------
    
    public void save(Event event) throws SQLException {
        
        try (PreparedStatement statement = connection.prepareStatement(SQL_POST_EVENT))
        {
            java.sql.Date formattedDate = java.sql.Date.valueOf(DateFormat.getDateInstance(DateFormat.SHORT, Locale.ROOT).format(event.getEvent_date()));
            statement.setDate(1, formattedDate);

            statement.setString(2, event.getCreator().getUsername());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    // ---------------------------------------------------------------------------------------------------------




}
