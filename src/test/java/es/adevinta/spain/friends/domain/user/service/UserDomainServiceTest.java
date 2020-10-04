package es.adevinta.spain.friends.domain.user.service;

import es.adevinta.spain.friends.UserTestUtils;
import es.adevinta.spain.friends.application.user.registration.model.NotValidRegisterException;
import es.adevinta.spain.friends.application.user.registration.model.UserNotFoundException;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import es.adevinta.spain.friends.infra.user.repository.UserRepository;
import es.adevinta.spain.friends.infra.user.repository.UserRepositoryInMemory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDomainServiceTest {

    private UserDomainService userDomainService;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepositoryInMemory(new UserEntityConverter());
        userDomainService = new UserDomainService(userRepository);
    }

    @Test
    public void shouldReturnUsersSuccessfully() throws NotValidRegisterException, UserNotFoundException {
        User user = UserTestUtils.randomUser();
        assertFalse(userRepository.findByUsername(user.username()).isPresent());
        userDomainService.register(user);
        assertTrue(userRepository.findByUsername(user.username()).isPresent());
        User userCopy = userDomainService.user(user.username());
        assertEquals(user.username().value(), userCopy.username().value());
        assertEquals(user.password().value(), userCopy.password().value());
    }

    @Test
    public void shouldRegisterUsersSuccessfully() throws NotValidRegisterException {
        User user = UserTestUtils.randomUser();
        assertFalse(userRepository.findByUsername(user.username()).isPresent());
        userDomainService.register(user);
        assertTrue(userRepository.findByUsername(user.username()).isPresent());
    }

    @Test(expected = NotValidRegisterException.class)
    public void shouldNotRegisterDuplicatedUsers() throws NotValidRegisterException {
        User user = UserTestUtils.randomUser();
        assertFalse(userRepository.findByUsername(user.username()).isPresent());
        userDomainService.register(user);
        assertTrue(userRepository.findByUsername(user.username()).isPresent());
        userDomainService.register(user);
    }
}
