package toy.shop.modules.footboll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("팀 등록이 제대로 되는지 테스트")
    @Test
    void saveTeam() {
        Team team = Team.builder()
                .league(League.EPL)
                .name("리버풀")
                .build();

        Team savedTeam = teamRepository.save(team);

        assertEquals(savedTeam.getName(), team.getName());
    }

    @DisplayName("리그, 이름 제약조건에 대한 통과하는 테스트")
    @Test
    void saveTeam_League_Team_Unique() {
        Team team1 = Team.builder()
                .league(League.EPL)
                .name("리버풀")
                .build();
        teamRepository.save(team1);

        Team team2 = Team.builder()
                .league(League.LALIGA)
                .name("리버풀")
                .build();
        Team savedTeam2 = teamRepository.save(team2);

        assertEquals(savedTeam2.getLeague(), League.LALIGA);
    }

    @DisplayName("리그, 이름 제약조건에 대해 실패하는 테스트")
    @Test
    void saveTeam_League_Team_Unique_Fail() {
        Team team1 = Team.builder()
                .league(League.EPL)
                .name("리버풀")
                .build();
        teamRepository.save(team1);

        Team team2 = Team.builder()
                .league(League.EPL)
                .name("리버풀")
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> teamRepository.save(team2));

    }

}