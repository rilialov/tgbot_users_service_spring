package tgbot.users_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tgbot.users.service.UserDTO;
import tgbot.users_service.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Mapping(target = "chatID", source = "chatId")
    UserDTO userToUserDTO(User user);
    @Mapping(target = "chatId", source = "chatID")
    User userDTOToUser(UserDTO userDTO);
    List<UserDTO> userListToDTOUserList(List<User> userList);
    List<User> userListDTOToUserList(List<UserDTO> userDTOList);
}
