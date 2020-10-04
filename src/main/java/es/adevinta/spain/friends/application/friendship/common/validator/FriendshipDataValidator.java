package es.adevinta.spain.friends.application.friendship.common.validator;

import es.adevinta.spain.friends.application.user.registration.model.IncorrectPasswordException;
import es.adevinta.spain.friends.application.user.registration.service.UserService;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FriendshipDataValidator {

    private UserService userService;

    private UserDomainService userDomainService;

    @Autowired
    public FriendshipDataValidator(UserService userService, UserDomainService userDomainService) {
        this.userService = userService;
        this.userDomainService = userDomainService;
    }

    public void authorize(User user, String password) throws IncorrectPasswordException {
        user.validate(Password.create(password));
    }
}
