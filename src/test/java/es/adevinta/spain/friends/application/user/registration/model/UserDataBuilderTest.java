package es.adevinta.spain.friends.application.user.registration.model;

import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.infra.user.UserUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDataBuilderTest {

    @Test
    public void shouldBuilderCreateUserDataSuccessfully() throws NotValidRegisterException {
        UserData data = UserDataBuilder.builder()
                .withUsername("username")
                .withPassword("12345678")
            .build();
        assertEquals("username", data.username());
        assertEquals("12345678", data.password());
    }

    @Test(expected = NotValidRegisterException.class)
    public void shouldFailIfUsernameIsTooSmall() throws NotValidRegisterException {
        User user = UserUtils.randomUser();
        UserData data = UserDataBuilder.builder()
                .withUsername("user")
                .withPassword(user.password().value())
            .build();
    }

    @Test(expected = NotValidRegisterException.class)
    public void shouldFailIfUsernameIsTooLarge() throws NotValidRegisterException {
        User user = UserUtils.randomUser();
        UserData data = UserDataBuilder.builder()
                .withUsername("username1234567890")
                .withPassword(user.password().value())
            .build();
    }

    @Test(expected = NotValidRegisterException.class)
    public void shouldFailIfUsernameIsNotAlphanumeric() throws NotValidRegisterException {
        User user = UserUtils.randomUser();
        UserData data = UserDataBuilder.builder()
                .withUsername("user_name")
                .withPassword(user.password().value())
            .build();
    }

    @Test(expected = NotValidRegisterException.class)
    public void shouldFailIfPasswordIsTooSmall() throws NotValidRegisterException {
        User user = UserUtils.randomUser();
        UserData data = UserDataBuilder.builder()
                .withUsername(user.username().value())
                .withPassword("123")
            .build();
    }

    @Test(expected = NotValidRegisterException.class)
    public void shouldFailIfPasswordIsTooLarge() throws NotValidRegisterException {
        User user = UserUtils.randomUser();
        UserData data = UserDataBuilder.builder()
                .withUsername(user.username().value())
                .withPassword("abcdefghijk1234567890")
            .build();
    }

    @Test(expected = NotValidRegisterException.class)
    public void shouldFailIfPasswordIsNotAlphanumeric() throws NotValidRegisterException {
        User user = UserUtils.randomUser();
        UserData data = UserDataBuilder.builder()
                .withUsername(user.username().value())
                .withPassword("1_2_3")
            .build();
    }
}
