package es.adevinta.spain.friends.domain.user.service;

import es.adevinta.spain.friends.application.user.registration.model.NotValidRegisterException;
import es.adevinta.spain.friends.application.user.registration.model.UserNotFoundException;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDomainService {

    private final UserRepository userRepository;

    @Autowired
    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User user(Username username) throws UserNotFoundException {
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            return found.get();
        }
        throw new UserNotFoundException("User not found");
    }

    public boolean exists(User user) {
        Optional<User> found = userRepository.findByUsername(user.username());
        return found.isPresent();
    }

    public void register(User user) throws NotValidRegisterException {
        if (exists(user)) {
            throw new NotValidRegisterException("User in use");
        }
        userRepository.save(user);
    }
}
