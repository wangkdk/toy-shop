package toy.shop.modules.football;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public void saveTeam(League league, String name) {
        Team team = Team.builder()
                .league(league)
                .name(name)
                .build();

        teamRepository.save(team);
    }

    @Transactional
    public void modifyTeam(Long id, League league, String name) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));

        log.info("===== TeamService.modifyTeam =====");
        team.updateTeam(league, name);
    }

    @Transactional
    public void removeTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));
        teamRepository.delete(team);
    }
}
