package es.adevinta.spain.friends.application.user.registration.model;

public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
