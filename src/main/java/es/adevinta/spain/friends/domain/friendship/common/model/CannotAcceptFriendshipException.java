package es.adevinta.spain.friends.domain.friendship.common.model;

public class CannotAcceptFriendshipException extends Exception {

    public CannotAcceptFriendshipException(String message) {
        super(message);
    }
}
