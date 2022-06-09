package tgbot.users_service.endpoint;

import org.mapstruct.factory.Mappers;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tgbot.users.service.*;
import tgbot.users_service.entity.Team;
import tgbot.users_service.mappers.TeamMapper;
import tgbot.users_service.repository.TeamRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Endpoint
public class TeamEndpoint {

    private static final String NAMESPACE_URI = "http://users.tgbot/service";

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper = Mappers.getMapper(TeamMapper.class);

    public TeamEndpoint(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTeamByIdRequest")
    @ResponsePayload
    public GetTeamResponse getTeamById(@RequestPayload GetTeamByIdRequest request) {
        GetTeamResponse response = new GetTeamResponse();

        Optional<Team> optional = teamRepository.findById(request.getId());
        optional.ifPresent(team -> response.setTeamDTO(teamMapper.teamToTeamDTO(team)));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllTeamsRequest")
    @ResponsePayload
    public GetAllTeamsResponse getAllTeams(@RequestPayload GetAllTeamsRequest request) {
        GetAllTeamsResponse response = new GetAllTeamsResponse();

        Iterable<Team> iterable = teamRepository.findAll();
        List<Team> teamList = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        List<TeamDTO> teamDTOList = teamMapper.teamListToDTOTeamList(teamList);
        response.setTeamsList(teamDTOList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveTeamRequest")
    @ResponsePayload
    public GetTeamResponse saveTeam(@RequestPayload SaveTeamRequest request) {
        GetTeamResponse response = new GetTeamResponse();

        Team dtoToTeam = teamMapper.teamDTOToTeam(request.getTeamDTO());
        Team saved = teamRepository.save(dtoToTeam);
        response.setTeamDTO(teamMapper.teamToTeamDTO(saved));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteTeamByIdRequest")
    @ResponsePayload
    public GetBooleanResponse deleteTeamById(@RequestPayload DeleteTeamByIdRequest request) {
        GetBooleanResponse response = new GetBooleanResponse();

        teamRepository.deleteById(request.getId());
        response.setDeleted(true);
        return response;
    }
}
