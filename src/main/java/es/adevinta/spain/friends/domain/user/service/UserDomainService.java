package es.adevinta.spain.friends.domain.user.service;

import es.adevinta.spain.friends.application.user.registration.model.NotValidRegisterException;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.infra.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDomainService {

    private UserRepository userRepository;

    @Autowired
    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Domain Service - User Registration
     *
     * @param user
     * @throws NotValidRegisterException
     */
    public void register(User user) throws NotValidRegisterException {
        Optional<User> found = userRepository.findByUsername(user.username());
        if (found.isPresent()) {
            throw new NotValidRegisterException("User in use");
        }
        userRepository.save(user);
    }
}
