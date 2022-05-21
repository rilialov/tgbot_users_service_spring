package tgbot.users_service.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tgbot.users_service.repository.UserRepository;
import tgbot.users_service.web_service.GetUserRequest;
import tgbot.users_service.web_service.GetUserResponse;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://users_service.tgbot/web-service";

    private final UserRepository userRepository;

    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(userRepository.findUser(request.getFirstName()));
        return response;
    }

}
