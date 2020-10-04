package es.adevinta.spain.friends.application.friendship.common.validator;

import es.adevinta.spain.friends.application.user.registration.model.IncorrectPasswordException;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FriendshipDataValidator {

    @Autowired
    public FriendshipDataValidator() {
    }

    public void authorize(User user, String password) throws IncorrectPasswordException {
        user.validate(Password.create(password));
    }
}
