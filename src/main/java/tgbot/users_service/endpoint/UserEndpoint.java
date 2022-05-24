package tgbot.users_service.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tgbot.users.service.GetUserByIdRequest;
import tgbot.users.service.GetUserResponse;
import tgbot.users_service.converters.UserConverter;
import tgbot.users_service.entity.User;
import tgbot.users_service.repository.UserRepository;

import java.util.Optional;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://users.tgbot/service";

    private final UserRepository userRepository;
    private UserConverter userConverter = new UserConverter();

    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserByIdRequest request) {
        GetUserResponse response = new GetUserResponse();

        Optional<User> optional = userRepository.findById(request.getChatId());
        optional.ifPresent(user -> response.setUserDTO(userConverter.userToUserDTO(user)));
        return response;
    }

}
