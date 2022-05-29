package tgbot.users_service.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tgbot.users_service.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryIT {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByChatID() {
        Optional<User> optional = userRepository.findById(1L);

        assertTrue(optional.isPresent());
        assertEquals("Porter", optional.get().getFirstName());
    }

    @Test
    void findByNickname() {
        Optional<User> optional = userRepository.findByNickname("magna");

        assertTrue(optional.isPresent());
        assertEquals("Desiree", optional.get().getFirstName());
    }

    @Test
    void findAll() {
        Iterable<User> iterable = userRepository.findAll();
        List<User> userList =
                StreamSupport.stream(iterable.spliterator(), false)
                        .collect(Collectors.toList());

        assertNotNull(userList);
        assertTrue(userList.size() > 0);
    }

    @Test
    void create() {
        User user = new User(18L, "Test FirstName");

        User saved = userRepository.save(user);

        Optional<User> optional = userRepository.findById(saved.getChatId());
        assertTrue(optional.isPresent());
        assertEquals("Test FirstName", optional.get().getFirstName());
    }

    @Test
    void save() {
        Optional<User> optional = userRepository.findById(1L);

        assertTrue(optional.isPresent());
        User user = optional.get();
        user.setFirstName("Potter");
        userRepository.save(user);

        Optional<User> changed = userRepository.findById(1L);
        assertTrue(changed.isPresent());
        assertEquals("Potter", changed.get().getFirstName());
    }

    @Test
    void deleteById() {
        userRepository.deleteById(10L);

        Optional<User> deleted = userRepository.findById(10L);
        assertFalse(deleted.isPresent());
    }

    @Test
    void delete() {
        Optional<User> optional = userRepository.findById(11L);

        assertTrue(optional.isPresent());
        userRepository.delete(optional.get());

        Optional<User> deleted = userRepository.findById(11L);
        assertFalse(deleted.isPresent());
    }
}