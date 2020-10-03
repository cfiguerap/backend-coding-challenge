package es.adevinta.spain.friends.infra.user.model;

import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.UserBuilder;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.common.EntityConverter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityConverter extends EntityConverter<User, UserEntity> {

    @Override
    public UserEntity target(User t) {
        return new UserEntity(t.username().value(), t.password().value());
    }

    @Override
    public User source(UserEntity r) {
        return UserBuilder.builder()
                .withUsername(Username.create(r.username()))
                .withPassword(Password.create(r.password()))
            .build();
    }
}
