package tgbot.users_service.repository;

import org.springframework.data.repository.CrudRepository;
import tgbot.users_service.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
}
