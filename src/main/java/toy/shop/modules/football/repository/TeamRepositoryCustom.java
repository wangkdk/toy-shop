package toy.shop.modules.football.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.shop.modules.football.controller.form.TeamSearchCondition;
import toy.shop.modules.football.dto.TeamListDto;

public interface TeamRepositoryCustom {

    Page<TeamListDto> searchPage(Pageable pageable, TeamSearchCondition teamSearchCondition);
}
