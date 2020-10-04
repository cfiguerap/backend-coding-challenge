package es.adevinta.spain.friends.infra.user.repository;

import es.adevinta.spain.friends.UserTestUtils;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserRepositoryInMemoryTest {

    private UserRepositoryInMemory userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepositoryInMemory(new UserEntityConverter());
    }

    @Test
    public void shouldNotFindNotExistentUsers() {
        assertFalse(userRepository.exists(Username.create("not_exists")));
    }

    @Test
    public void shouldFindAndSaveUsersSuccessfully() {
        User user = UserTestUtils.randomUser();
        assertFalse(userRepository.exists(user.username()));
        userRepository.save(user);
        assertTrue(userRepository.exists(user.username()));
    }

    @Test
    public void shouldRemoveUsersSuccessfully() {
        User user = UserTestUtils.randomUser();
        userRepository.save(user);
        assertTrue(userRepository.exists(user.username()));
        userRepository.remove(user);
        assertFalse(userRepository.exists(user.username()));
    }
}
