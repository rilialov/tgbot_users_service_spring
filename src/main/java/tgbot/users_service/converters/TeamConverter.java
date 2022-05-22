package tgbot.users_service.converters;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import tgbot.users_service.entity.Team;
import tgbot.users_service.web_service.TeamDTO;


import java.util.List;

public class TeamConverter {
    private final ModelMapper modelMapper;

    public TeamConverter() {
        modelMapper = new ModelMapper();
    }

    public TeamDTO teamToTeamDTO (Team team) {
        return modelMapper.map(team, TeamDTO.class);
    }

    public Team teamDTOToTeam (TeamDTO teamDTO) {
        return modelMapper.map(teamDTO, Team.class);
    }

    public List<TeamDTO> teamListToDTOTeamList(List<Team> teamList) {
        return modelMapper.map(teamList, new TypeToken<List<TeamDTO>>() {}.getType());
    }

    public List<Team> teamListDTOToTeamList(List<TeamDTO> teamDTOList) {
        return modelMapper.map(teamDTOList, new TypeToken<List<Team>>() {}.getType());
    }
}
