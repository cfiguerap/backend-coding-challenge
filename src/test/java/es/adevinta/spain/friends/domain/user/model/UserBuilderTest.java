package es.adevinta.spain.friends.domain.user.model;

import es.adevinta.spain.friends.application.user.registration.model.NotValidRegisterException;
import es.adevinta.spain.friends.domain.user.service.UserDomainService;
import es.adevinta.spain.friends.infra.user.UserUtils;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import es.adevinta.spain.friends.infra.user.repository.UserRepository;
import es.adevinta.spain.friends.infra.user.repository.UserRepositoryInMemory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserBuilderTest {

    @Test
    public void shouldBuilderCreateUsersSuccessfully() {
        User user1 = UserUtils.randomUser();
        User user2 = UserBuilder.builder()
                .withUsername(user1.username())
                .withPassword(user1.password())
            .build();
        assertEquals(user1, user2);
    }
}
