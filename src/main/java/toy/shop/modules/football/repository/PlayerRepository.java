package toy.shop.modules.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.shop.modules.football.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
