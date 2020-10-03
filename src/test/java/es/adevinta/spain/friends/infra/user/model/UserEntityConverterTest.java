package es.adevinta.spain.friends.infra.user.model;

import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.UserBuilder;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.user.UserUtils;
import es.adevinta.spain.friends.infra.user.repository.UserRepositoryInMemory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class UserEntityConverterTest {

    private UserEntityConverter converter = new UserEntityConverter();

    @Test
    public void shouldConvertUserToEntitySuccessfully() {
        User user = UserUtils.randomUser();
        UserEntity entity = converter.target(user);

        assertNotNull(entity);
        assertEquals(user.username().value(), entity.username());
        assertEquals(user.password().value(), entity.password());
    }

    @Test
    public void shouldConvertEntityToUsersSuccessfully() {
        User user = UserUtils.randomUser();
        UserEntity entity = new UserEntity(user.username().value(), user.password().value());
        User newUser = converter.source(entity);

        assertNotNull(entity);
        assertEquals(user.username(), newUser.username());
        assertEquals(user.password(), newUser.password());
    }

    @Test
    public void shouldConvertUsersToEntitiesSuccessfully() {
        List<User> users = new ArrayList<>();
        for (int i=0; i<5; i++) {
            users.add(UserUtils.randomUser());
        }
        List<UserEntity> entities = converter.target(users);

        assertNotNull(entities);
        assertEquals(users.size(), entities.size());
        for (int i=0; i< users.size(); i++) {
            assertEquals(users.get(i).username().value(), entities.get(i).username());
            assertEquals(users.get(i).password().value(), entities.get(i).password());
        }
    }

    @Test
    public void shouldConvertEntitiesToUsersSuccessfully() {
        List<UserEntity> entities = new ArrayList<>();
        for (int i=0; i<5; i++) {
            User user = UserUtils.randomUser();
            UserEntity entity = new UserEntity(user.username().value(), user.password().value());
            entities.add(entity);
        }
        List<User> users = converter.source(entities);

        assertNotNull(users);
        assertEquals(entities.size(), users.size());
        for (int i=0; i< users.size(); i++) {
            assertEquals(entities.get(i).username(), users.get(i).username().value());
            assertEquals(entities.get(i).password(), users.get(i).password().value());
        }
    }
}
