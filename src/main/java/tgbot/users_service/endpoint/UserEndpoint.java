package tgbot.users_service.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tgbot.users.service.*;
import tgbot.users_service.converters.UserConverter;
import tgbot.users_service.entity.User;
import tgbot.users_service.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://users.tgbot/service";

    private final UserRepository userRepository;
    private final UserConverter userConverter = new UserConverter();

    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserResponse getUserById(@RequestPayload GetUserByIdRequest request) {
        GetUserResponse response = new GetUserResponse();

        Optional<User> optional = userRepository.findById(request.getChatId());
        optional.ifPresent(user -> response.setUserDTO(userConverter.userToUserDTO(user)));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByNickRequest")
    @ResponsePayload
    public GetUserResponse getUserByNickname(@RequestPayload GetUserByNickRequest request) {
        GetUserResponse response = new GetUserResponse();

        Optional<User> optional = userRepository.findByNickname(request.getNickname());
        optional.ifPresent(user -> response.setUserDTO(userConverter.userToUserDTO(user)));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {
        GetAllUsersResponse response = new GetAllUsersResponse();

        Iterable<User> iterable = userRepository.findAll();
        List<User> userList =
                StreamSupport.stream(iterable.spliterator(), false)
                        .collect(Collectors.toList());
        List<UserDTO> userDTOList = userConverter.userListToDTOUserList(userList);
        response.setUsersList(userDTOList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveUserRequest")
    @ResponsePayload
    public GetUserResponse saveUser(@RequestPayload SaveUserRequest request) {
        GetUserResponse response = new GetUserResponse();

        User dtoToUser = userConverter.userDTOToUser(request.getUserDTO());
        User saved = userRepository.save(dtoToUser);
        response.setUserDTO(userConverter.userToUserDTO(saved));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserByIdRequest")
    @ResponsePayload
    public GetBooleanResponse deleteUserById(@RequestPayload DeleteUserByIdRequest request) {
        GetBooleanResponse response = new GetBooleanResponse();

        userRepository.deleteById(request.getChatId());
        response.setDeleted(true);
        return response;
    }
}
