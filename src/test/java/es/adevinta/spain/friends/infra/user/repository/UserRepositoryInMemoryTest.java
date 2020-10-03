package es.adevinta.spain.friends.infra.user.repository;

import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.user.UserUtils;
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
        assertFalse(userRepository.findByUsername(Username.create("not_exists")).isPresent());
    }

    @Test
    public void shouldFindAndSaveUsersSuccessfully() {
        User user = UserUtils.randomUser();
        assertFalse(userRepository.findByUsername(user.username()).isPresent());
        userRepository.save(user);
        assertTrue(userRepository.findByUsername(user.username()).isPresent());
    }

    @Test
    public void shouldRemoveUsersSuccessfully() {
        User user = UserUtils.randomUser();
        userRepository.save(user);
        assertTrue(userRepository.findByUsername(user.username()).isPresent());
        userRepository.remove(user);
        assertFalse(userRepository.findByUsername(user.username()).isPresent());
    }
}
