package es.adevinta.spain.friends.infra.user.repository;

import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

    public Optional<User> findByUsername(Username username);

    public void save(User user);

    public void remove(User user);
}
