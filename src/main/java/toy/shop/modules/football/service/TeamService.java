package toy.shop.modules.football.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.shop.modules.football.entity.League;
import toy.shop.modules.football.entity.Team;
import toy.shop.modules.football.repository.TeamRepository;

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

    @Transactional
    public void generateTestData() {
        for (int i=0; i<30; i++) {
            String randValue = RandomString.make(5);
            Team team = Team.builder()
                    .league(League.EPL)
                    .name(randValue)
                    .build();
            teamRepository.save(team);
        }

        for (int i=0; i<30; i++) {
            String randValue = RandomString.make(5);
            Team team = Team.builder()
                    .league(League.LALIGA)
                    .name(randValue)
                    .build();
            teamRepository.save(team);
        }
    }
}
