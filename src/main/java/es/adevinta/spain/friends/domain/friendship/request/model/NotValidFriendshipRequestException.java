package es.adevinta.spain.friends.domain.friendship.request.model;

public class NotValidFriendshipRequestException extends Exception {

    public NotValidFriendshipRequestException(String message) {
        super(message);
    }
}
