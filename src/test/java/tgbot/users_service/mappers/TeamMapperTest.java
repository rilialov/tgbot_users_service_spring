package tgbot.users_service.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import tgbot.users.service.TeamDTO;
import tgbot.users_service.entity.Team;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamMapperTest {

    private final TeamMapper teamMapper = Mappers.getMapper(TeamMapper.class);

    @Test
    void teamToTeamDTO() {
        Team team = new Team("Blue sky", "Blue");

        TeamDTO teamDTO = teamMapper.teamToTeamDTO(team);

        assertEquals(team.getTeamName(), teamDTO.getTeamName());
        assertEquals(team.getTeamColor(), teamDTO.getTeamColor());
    }

    @Test
    void teamDTOToTeam() {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName("Blue sky");
        teamDTO.setTeamColor("Blue");

        Team team = teamMapper.teamDTOToTeam(teamDTO);

        assertEquals(teamDTO.getTeamName(), team.getTeamName());
        assertEquals(teamDTO.getTeamColor(), team.getTeamColor());
    }

    @Test
    void teamListToDTOTeamList() {
        List<Team> list = new ArrayList<>();
        Team team = new Team("Blue sky", "Blue");
        list.add(team);

        List<TeamDTO> dtoList = teamMapper.teamListToDTOTeamList(list);

        assertEquals(list.get(0).getTeamName(), dtoList.get(0).getTeamName());
        assertEquals(list.get(0).getTeamColor(), dtoList.get(0).getTeamColor());
    }

    @Test
    void teamListDTOToTeamList() {
        List<TeamDTO> dtoList = new ArrayList<>();
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName("Blue sky");
        teamDTO.setTeamColor("Blue");
        dtoList.add(teamDTO);

        List<Team> list = teamMapper.teamListDTOToTeamList(dtoList);

        assertEquals(dtoList.get(0).getTeamName(), list.get(0).getTeamName());
        assertEquals(dtoList.get(0).getTeamColor(), list.get(0).getTeamColor());
    }
}