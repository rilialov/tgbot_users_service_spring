package tgbot.users_service.repository;

import org.springframework.data.repository.CrudRepository;
import tgbot.users_service.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
