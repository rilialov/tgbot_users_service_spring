package tgbot.users_service.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tgbot.users.service.TeamDTO;
import tgbot.users_service.entity.Team;
import tgbot.users_service.mappers.TeamMapper;
import tgbot.users_service.repository.TeamRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeamsService {

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper = Mappers.getMapper(TeamMapper.class);

    private List<TeamDTO> teamDTOList;

    public TeamsService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        setList();
    }

    private void setList() {
        Iterable<Team> iterable = teamRepository.findAll();
        List<Team> teamList = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        teamDTOList = teamMapper.teamListToDTOTeamList(teamList);
    }

    public Page<TeamDTO> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TeamDTO> list;

        if (teamDTOList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, teamDTOList.size());
            list = teamDTOList.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), teamDTOList.size());
    }
}
