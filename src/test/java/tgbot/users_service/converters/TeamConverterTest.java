package tgbot.users_service.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tgbot.users_service.entity.Team;
import tgbot.users_service.web_service.TeamDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamConverterTest {
    private TeamConverter teamConverter;

    @BeforeEach
    void init() {
        teamConverter = new TeamConverter();
    }

    @Test
    void teamToTeamDTO() {
        Team team = new Team("Blue sky", "Blue");

        TeamDTO teamDTO = teamConverter.teamToTeamDTO(team);

        assertEquals("Blue sky", teamDTO.getTeamName());
        assertEquals("Blue", teamDTO.getTeamColor());
    }

    @Test
    void teamDTOToTeam() {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName("Blue sky");
        teamDTO.setTeamColor("Blue");

        Team team  = teamConverter.teamDTOToTeam(teamDTO);

        assertEquals("Blue sky", team.getTeamName());
        assertEquals("Blue", team.getColor());
    }

    @Test
    void teamListToDTOTeamList() {
        List<Team> list = new ArrayList<>();
        Team team = new Team("Blue sky", "Blue");
        list.add(team);

        List<TeamDTO> dtoList = teamConverter.teamListToDTOTeamList(list);

        assertEquals("Blue sky", dtoList.get(0).getTeamName());
        assertEquals("Blue", dtoList.get(0).getTeamColor());
    }

    @Test
    void teamListDTOToTeamList() {
        List<TeamDTO> dtoList = new ArrayList<>();
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName("Blue sky");
        teamDTO.setTeamColor("Blue");
        dtoList.add(teamDTO);

        List<Team> list = teamConverter.teamListDTOToTeamList(dtoList);

        assertEquals("Blue sky", list.get(0).getTeamName());
        assertEquals("Blue", list.get(0).getColor());
    }

}