package tgbot.users_service.repository;

import org.springframework.data.repository.CrudRepository;
import tgbot.users_service.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
