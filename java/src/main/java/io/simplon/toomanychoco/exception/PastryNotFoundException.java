package io.simplon.toomanychoco.exception;

public class PastryNotFoundException extends Exception {

    public PastryNotFoundException() {
		super("Pastry not found");
	}

	public PastryNotFoundException(String message) {
		super(message);
	}
}
