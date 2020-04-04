package toy.shop.modules.football;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByLeagueAndName(League league, String name);
}
