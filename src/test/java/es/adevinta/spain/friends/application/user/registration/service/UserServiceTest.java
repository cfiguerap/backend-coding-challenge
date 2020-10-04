package es.adevinta.spain.friends.application.user.registration.service;

import es.adevinta.spain.friends.application.user.registration.model.NotValidRegisterException;
import es.adevinta.spain.friends.application.user.registration.model.UserData;
import es.adevinta.spain.friends.application.user.registration.model.UserDataBuilder;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.domain.user.service.UserDomainService;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import es.adevinta.spain.friends.infra.user.repository.UserRepository;
import es.adevinta.spain.friends.infra.user.repository.UserRepositoryInMemory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepositoryInMemory(new UserEntityConverter());
        userService = new UserService(new UserDomainService(userRepository));
    }

    @Test
    public void shouldRegisterUserSuccessfully() throws NotValidRegisterException {
        UserData data = UserDataBuilder.builder()
                .withUsername("username1")
                .withPassword("successful")
            .build();
        assertFalse(userRepository.exists(Username.create("username1")));
        userService.register(data);
        assertTrue(userRepository.exists(Username.create("username1")));
    }

    @Test(expected = NotValidRegisterException.class)
    public void shouldNotRegisterUserBecauseInvalidPassword() throws NotValidRegisterException {
        UserData data = UserDataBuilder.builder()
                .withUsername("username2")
                .withPassword("%&$-incorrect-password-€?!")
            .build();
        assertFalse(userRepository.exists(Username.create("username1")));
        userService.register(data);
    }

}
