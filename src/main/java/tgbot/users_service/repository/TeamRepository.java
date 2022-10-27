package tgbot.users_service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import tgbot.users_service.entity.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
}
