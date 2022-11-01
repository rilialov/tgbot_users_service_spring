package tgbot.users_service.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tgbot.users.service.UserDTO;
import tgbot.users_service.entity.User;
import tgbot.users_service.mappers.UserMapper;
import tgbot.users_service.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsersService {

    private final UserRepository userRepository;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private List<UserDTO> userDTOList;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
        setList();
    }

    private void setList() {
        Iterable<User> iterable = userRepository.findAll();
        List<User> userList = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        userDTOList = userMapper.userListToDTOUserList(userList);
    }

    public Page<UserDTO> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserDTO> list;

        if (userDTOList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, userDTOList.size());
            list = userDTOList.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), userDTOList.size());
    }
}
