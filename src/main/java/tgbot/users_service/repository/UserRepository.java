package tgbot.users_service.repository;

import org.springframework.stereotype.Component;
import tgbot.users_service.web_service.Role;
import tgbot.users_service.web_service.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Component
public class UserRepository {

    private static final Map<String, User> userMap = new HashMap<>();

    @PostConstruct
    public void initData() {
        User user = new User();
        user.setChatID(1L);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setRole(Role.ADMIN);
        userMap.put(user.getFirstName(), user);
    }

    public User findUser(String firstName) {
        return userMap.get(firstName);
    }
}
