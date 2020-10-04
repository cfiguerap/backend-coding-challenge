package es.adevinta.spain.friends.legacy;

import es.adevinta.spain.friends.application.friendship.common.service.FriendshipService;
import es.adevinta.spain.friends.application.friendship.request.model.FriendshipRequestData;
import es.adevinta.spain.friends.application.friendship.request.model.FriendshipRequestDataBuilder;
import es.adevinta.spain.friends.application.friendship.request.service.FriendshipRequestService;
import es.adevinta.spain.friends.application.user.registration.model.IncorrectPasswordException;
import es.adevinta.spain.friends.application.user.registration.model.UserNotFoundException;
import es.adevinta.spain.friends.domain.friendship.common.model.CannotAcceptFriendshipException;
import es.adevinta.spain.friends.domain.friendship.common.model.CannotDeclineFriendshipException;
import es.adevinta.spain.friends.domain.friendship.request.model.NotValidFriendshipRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/friendship")
public class FriendshipLegacyController {

  @Autowired
  private FriendshipService friendshipService;

  @Autowired
  private FriendshipRequestService friendshipRequestService;

  @PostMapping("/request")
  void requestFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    try {
      FriendshipRequestData data = FriendshipRequestDataBuilder.builder()
          .withFrom(usernameFrom)
          .withPassword(password)
          .withTo(usernameTo)
        .build();
      friendshipRequestService.requestFriendship(data);
    } catch (NotValidFriendshipRequestException | UserNotFoundException | IncorrectPasswordException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/accept")
  void acceptFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    try {
      FriendshipRequestData data = FriendshipRequestDataBuilder.builder()
          .withFrom(usernameFrom)
          .withPassword(password)
          .withTo(usernameTo)
        .build();
      friendshipService.acceptFriendship(data);
    } catch (CannotAcceptFriendshipException | UserNotFoundException | IncorrectPasswordException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/decline")
  void declineFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    try {
      FriendshipRequestData data = FriendshipRequestDataBuilder.builder()
          .withFrom(usernameFrom)
          .withPassword(password)
          .withTo(usernameTo)
        .build();
      friendshipService.declineFriendship(data);
    } catch (CannotDeclineFriendshipException | UserNotFoundException | IncorrectPasswordException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/list")
  Object listFriends(
      @RequestParam("username") String username,
      @RequestHeader("X-Password") String password
  ) {
    throw new RuntimeException("not implemented yet!");
  }
}
