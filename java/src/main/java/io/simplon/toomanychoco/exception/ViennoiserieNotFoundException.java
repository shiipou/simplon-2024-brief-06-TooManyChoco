package io.simplon.toomanychoco.exception;

public class ViennoiserieNotFoundException extends Exception {

    public ViennoiserieNotFoundException() {
		super("Viennoiserie not found");
	}

	public ViennoiserieNotFoundException(String message) {
		super(message);
	}
}
