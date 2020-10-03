package es.adevinta.spain.friends.infra.user;

import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.UserBuilder;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;

import java.util.UUID;

public class UserUtils {

    private UserUtils() {
    }

    public static User randomUser() {
        return UserBuilder.builder()
                .withUsername(Username.create(UUID.randomUUID().toString().substring(0, 5)))
                .withPassword(Password.create(UUID.randomUUID().toString().substring(0, 5)))
                .build();
    }
}
