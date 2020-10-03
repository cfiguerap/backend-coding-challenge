package es.adevinta.spain.friends.application.user.registration.service;

import es.adevinta.spain.friends.application.user.registration.model.NotValidRegisterException;
import es.adevinta.spain.friends.application.user.registration.model.UserData;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.UserBuilder;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.domain.user.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private UserDomainService userDomainService;

    @Autowired
    public UserRegistrationService(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    /**
     * Application Service - User Registration
     *
     * @param userData
     * @throws NotValidRegisterException
     */
    public void register(UserData userData) throws NotValidRegisterException {
        User newUser = UserBuilder.builder()
                .withUsername(Username.create(userData.username()))
                .withPassword(Password.create(userData.password()))
            .build();
        userDomainService.register(newUser);
    }
}
