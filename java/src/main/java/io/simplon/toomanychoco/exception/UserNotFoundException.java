package io.simplon.toomanychoco.exception;

// Exception : class provenant de java
public class UserNotFoundException extends Exception {
    // constructeur qui crée une instance de l'exception avec un message par défaut
    public UserNotFoundException() {
        super("User not found");
    }

    // constructeur qui permet de personnaliser le message d'erreur
    public UserNotFoundException(String message) {
        super(message);
    }
}
