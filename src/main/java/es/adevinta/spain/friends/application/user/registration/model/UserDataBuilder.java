package es.adevinta.spain.friends.application.user.registration.model;

import es.adevinta.spain.friends.application.user.registration.validation.UserRegistrationValidator;

public class UserDataBuilder {

    private String username;
    private String password;

    public static UserDataBuilder builder() {
        return new UserDataBuilder();
    }

    public UserDataBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserData build() throws NotValidRegisterException {
        UserData userData = new UserData(username, password);
        UserRegistrationValidator.checkValidUserData(userData);
        return userData;
    }
}