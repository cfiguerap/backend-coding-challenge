package es.adevinta.spain.friends.application.user.registration.validation;

import es.adevinta.spain.friends.application.user.registration.model.NotValidRegisterException;
import es.adevinta.spain.friends.application.user.registration.model.UserData;
import es.adevinta.spain.friends.utils.AlphanumericUtils;
import org.springframework.util.StringUtils;

public class UserRegistrationValidator {

    private static final Integer USERNAME_MIN_SIZE = 5;
    private static final Integer USERNAME_MAX_SIZE = 10;

    private static final Integer PASSWORD_MIN_SIZE = 8;
    private static final Integer PASSWORD_MAX_SIZE = 12;


    /**
     *
     * @param userData
     * @throws NotValidRegisterException
     */
    public static void checkValidUserData(UserData userData) throws NotValidRegisterException {
        if (!checkValidValues(userData.username(), USERNAME_MIN_SIZE, USERNAME_MAX_SIZE)) {
            throw new NotValidRegisterException("Username is not valid");
        }
        if (!checkValidValues(userData.password(), PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE)) {
            throw new NotValidRegisterException("Password is not valid");
        }
    }

    /**
     *
     * @param input
     * @param minSize
     * @param maxSize
     * @return
     */
    private static boolean checkValidValues(String input, Integer minSize, Integer maxSize) {
        return !StringUtils.isEmpty(input)
                && AlphanumericUtils.isAlphanumeric(input)
                && input.length() >= minSize
                && input.length() <= maxSize;
    }
}
