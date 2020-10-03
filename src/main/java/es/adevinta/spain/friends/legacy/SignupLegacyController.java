package es.adevinta.spain.friends.legacy;

import es.adevinta.spain.friends.application.user.registration.model.NotValidRegisterException;
import es.adevinta.spain.friends.application.user.registration.model.UserData;
import es.adevinta.spain.friends.application.user.registration.model.UserDataBuilder;
import es.adevinta.spain.friends.application.user.registration.service.UserRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/signup")
public class SignupLegacyController {

  @Autowired
  private UserRegistrationService registrationService;

  @PostMapping
  void signUp(
      @RequestParam("username") String username,
      @RequestHeader("X-Password") String password
  ) {
    try {
      UserData userData = UserDataBuilder.builder()
              .withUsername(username)
              .withPassword(password)
          .build();
      registrationService.register(userData);

    } catch (NotValidRegisterException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
