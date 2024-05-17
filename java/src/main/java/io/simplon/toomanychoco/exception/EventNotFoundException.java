package io.simplon.toomanychoco.exception;

public class EventNotFoundException extends Exception {
    public EventNotFoundException() {
        super("Event not found");
    }

    public EventNotFoundException(String message) {
        super(message);
    }
}
