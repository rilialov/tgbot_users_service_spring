package tgbot.users_service.endpoint;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tgbot.users.service.*;
import tgbot.users_service.entity.User;
import tgbot.users_service.mappers.UserMapper;
import tgbot.users_service.repository.UserRepository;
import tgbot.users_service.service.UsersService;

import java.util.Optional;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://users.tgbot/service";

    private final UserRepository userRepository;

    private final UsersService usersService;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public UserEndpoint(UserRepository userRepository, UsersService usersService) {
        this.userRepository = userRepository;
        this.usersService = usersService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserResponse getUserById(@RequestPayload GetUserByIdRequest request) {
        GetUserResponse response = new GetUserResponse();

        Optional<User> optional = userRepository.findById(request.getChatId());
        optional.ifPresent(user -> response.setUserDTO(userMapper.userToUserDTO(user)));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByNickRequest")
    @ResponsePayload
    public GetUserResponse getUserByNickname(@RequestPayload GetUserByNickRequest request) {
        GetUserResponse response = new GetUserResponse();

        Optional<User> optional = userRepository.findByNickname(request.getNickname());
        optional.ifPresent(user -> response.setUserDTO(userMapper.userToUserDTO(user)));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {
        GetAllUsersResponse response = new GetAllUsersResponse();

        Page<UserDTO> paginated = usersService.findPaginated(PageRequest.of(request.getPage() - 1, request.getSize()));

        response.setTotalPages(paginated.getTotalPages());
        response.setUsersList(paginated.getContent());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveUserRequest")
    @ResponsePayload
    public GetUserResponse saveUser(@RequestPayload SaveUserRequest request) {
        GetUserResponse response = new GetUserResponse();

        User dtoToUser = userMapper.userDTOToUser(request.getUserDTO());
        User saved = userRepository.save(dtoToUser);
        response.setUserDTO(userMapper.userToUserDTO(saved));
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
