package toy.shop.modules.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.shop.modules.football.entity.League;
import toy.shop.modules.football.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByLeagueAndName(League league, String name);
}
