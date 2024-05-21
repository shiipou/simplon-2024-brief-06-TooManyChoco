package io.simplon.toomanychoco;

import org.junit.jupiter.api.Test;

import io.simplon.toomanychoco.model.Event;
import io.simplon.toomanychoco.repository.EventRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class EventRepositoryTest {
    @Test void eventFindAll() {
        List<Event> events = EventRepository.getInstance().findAll();
        assertEquals(1, events.size(), "Must have one event");
    }
}
