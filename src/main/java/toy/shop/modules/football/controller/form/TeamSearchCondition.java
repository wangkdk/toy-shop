package toy.shop.modules.football.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toy.shop.modules.football.entity.League;

@Getter
@Setter
@NoArgsConstructor
public class TeamSearchCondition {

    private League league;
    private String name;
}
