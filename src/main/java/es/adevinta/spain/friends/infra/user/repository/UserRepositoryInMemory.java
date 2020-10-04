package es.adevinta.spain.friends.infra.user.repository;

import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.user.model.UserEntity;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepositoryInMemory implements UserRepository {

    private final Map<String, UserEntity> users = new ConcurrentHashMap<>();

    private UserEntityConverter converter;

    @Autowired
    public UserRepositoryInMemory(UserEntityConverter converter) {
        this.converter = converter;
    }

    @Override
    public boolean exists(Username username) {
        return findByUsername(username).isPresent();
    }

    @Override
    public Optional<User> findByUsername(Username username) {
        UserEntity entity = users.get(username.value());
        return (entity == null) ? Optional.empty() : Optional.of(converter.source(entity));
    }

    @Override
    public void save(User user) {
        users.put(user.username().value(), converter.target(user));
    }

    @Override
    public void remove(User user) {
        users.remove(user.username().toString());
    }
}
