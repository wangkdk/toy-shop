package toy.shop.modules.football;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
