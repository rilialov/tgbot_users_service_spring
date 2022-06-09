package tgbot.users_service.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import tgbot.users.service.UserDTO;
import tgbot.users_service.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void userToUserDTO() {
        User user = new User(20, "First Name");

        UserDTO userDTO = userMapper.userToUserDTO(user);

        assertEquals(user.getChatId(), userDTO.getChatID());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
    }

    @Test
    void userDTOToUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setChatID(20);
        userDTO.setFirstName("First Name");

        User user = userMapper.userDTOToUser(userDTO);

        assertEquals(userDTO.getChatID(), user.getChatId());
        assertEquals(userDTO.getFirstName(), user.getFirstName());
    }

    @Test
    void userListToDTOUserList() {
        List<User> list = new ArrayList<>();
        User user = new User(20, "First Name");
        list.add(user);

        List<UserDTO> dtoList = userMapper.userListToDTOUserList(list);

        assertEquals(list.get(0).getFirstName(), dtoList.get(0).getFirstName());
    }

    @Test
    void userListDTOToUserList() {
        List<UserDTO> dtoList = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setChatID(20);
        userDTO.setFirstName("First Name");
        dtoList.add(userDTO);

        List<User> list = userMapper.userListDTOToUserList(dtoList);

        assertEquals(dtoList.get(0).getFirstName(), list.get(0).getFirstName());
    }
}