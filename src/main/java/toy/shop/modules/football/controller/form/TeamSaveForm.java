package toy.shop.modules.football.controller.form;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toy.shop.modules.football.entity.League;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TeamSaveForm {

    private League league;

    @NotBlank
    private String name;

    @Builder
    public TeamSaveForm(League league, String name) {
        this.league = league;
        this.name = name;
    }

}
