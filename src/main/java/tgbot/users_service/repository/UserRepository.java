package tgbot.users_service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import tgbot.users_service.entity.User;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
}
