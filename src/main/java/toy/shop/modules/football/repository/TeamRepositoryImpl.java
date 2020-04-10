package toy.shop.modules.football.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import toy.shop.modules.football.controller.form.TeamSearchCondition;
import toy.shop.modules.football.dto.QTeamListDto;
import toy.shop.modules.football.dto.TeamListDto;
import toy.shop.modules.football.entity.League;
import toy.shop.modules.football.entity.Team;

import javax.persistence.EntityManager;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static toy.shop.modules.football.entity.QTeam.team;

public class TeamRepositoryImpl extends QuerydslRepositorySupport implements TeamRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TeamRepositoryImpl(EntityManager em) {
        super(Team.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<TeamListDto> searchPage(Pageable pageable, TeamSearchCondition condition) {

        QueryResults<TeamListDto> queryResults = queryFactory.
                select(new QTeamListDto(
                        team.id,
                        team.name,
                        team.league
                ))
                .from(team)
                .where(
                        leagueEq(condition.getLeague()),
                        nameEq(condition.getName())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<TeamListDto> content = queryResults.getResults();
        long total = queryResults.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression leagueEq(League league) {
        return league != null ? team.league.eq(league) : null;
    }

    private BooleanExpression nameEq(String name) {
        return hasText(name) ? team.name.eq(name) : null;
    }

}
