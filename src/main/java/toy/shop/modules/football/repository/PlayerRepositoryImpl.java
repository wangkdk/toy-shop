package toy.shop.modules.football.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import toy.shop.modules.football.entity.Player;

import javax.persistence.EntityManager;

public class PlayerRepositoryImpl extends QuerydslRepositorySupport implements PlayerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PlayerRepositoryImpl(EntityManager em) {
        super(Player.class);
        this.queryFactory = new JPAQueryFactory(em);
    }
}
