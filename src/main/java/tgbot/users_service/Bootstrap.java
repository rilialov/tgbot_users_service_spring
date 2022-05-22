package tgbot.users_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tgbot.users_service.entity.Team;
import tgbot.users_service.entity.User;
import tgbot.users_service.repository.TeamRepository;
import tgbot.users_service.repository.UserRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private final TeamRepository teamRepository;

    private final UserRepository userRepository;

    public Bootstrap(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        Iterable<Team> teams = teamRepository.findAll();
        for (Team team : teams) {
            System.out.println(team.getTeamName());
        }

        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            System.out.println(user.getFirstName());
        }

    }
}
