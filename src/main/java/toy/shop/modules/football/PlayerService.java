package toy.shop.modules.football;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void savePlayer(Long teamId, String name, Integer number) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("해당 팀이 존재하지 않습니다."));
        Player player = Player.builder()
                .name(name)
                .number(number)
                .team(team)
                .build();

        playerRepository.save(player);
    }
}
