package es.adevinta.spain.friends.application.user.registration.model;

public class NotValidRegisterException extends Exception {

    public NotValidRegisterException(String message) {
        super(message);
    }
}
