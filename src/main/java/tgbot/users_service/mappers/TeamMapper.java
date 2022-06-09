package tgbot.users_service.mappers;

import org.mapstruct.Mapper;
import tgbot.users.service.TeamDTO;
import tgbot.users_service.entity.Team;

import java.util.List;

@Mapper
public interface TeamMapper {
    TeamDTO teamToTeamDTO (Team team);
    Team teamDTOToTeam (TeamDTO teamDTO);
    List<TeamDTO> teamListToDTOTeamList(List<Team> teamList);
    List<Team> teamListDTOToTeamList(List<TeamDTO> teamDTOList);
}
