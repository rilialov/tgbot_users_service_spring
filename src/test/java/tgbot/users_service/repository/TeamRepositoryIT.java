package tgbot.users_service.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tgbot.users_service.entity.Team;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeamRepositoryIT {

    @Autowired
    TeamRepository teamRepository;

    @Test
    void findByID() {
        Optional<Team> optional = teamRepository.findById(1L);

        assertTrue(optional.isPresent());
        assertEquals("Grey", optional.get().getColor());
    }

    @Test
    void findAll() {
        Iterable<Team> iterable = teamRepository.findAll();
        List<Team> teamList = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());

        assertNotNull(teamList);
        assertTrue(teamList.size() > 0);
    }

    @Test
    void create() {
        Team team = new Team("Test team name", "Test team color");

        Team saved = teamRepository.save(team);

        Optional<Team> optional = teamRepository.findById(saved.getId());
        assertTrue(optional.isPresent());
        assertEquals("Test team color", optional.get().getColor());
    }

    @Test
    void save() {
        Optional<Team> optional = teamRepository.findById(1L);

        assertTrue(optional.isPresent());
        Team team = optional.get();
        team.setColor("Violet");
        teamRepository.save(team);

        Optional<Team> changed = teamRepository.findById(1L);
        assertTrue(changed.isPresent());
        assertEquals("Violet", changed.get().getColor());
    }

    @Test
    void deleteById() {
        teamRepository.deleteById(8L);

        Optional<Team> deleted = teamRepository.findById(8L);
        assertFalse(deleted.isPresent());
    }

    @Test
    void delete() {
        Optional<Team> optional = teamRepository.findById(9L);

        assertTrue(optional.isPresent());
        teamRepository.delete(optional.get());

        Optional<Team> deleted = teamRepository.findById(9L);
        assertFalse(deleted.isPresent());
    }
}