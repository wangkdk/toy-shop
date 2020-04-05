package toy.shop.modules.football.form;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toy.shop.modules.football.League;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TeamModifyForm {

    private Long id;

    private League league;

    @NotBlank
    private String name;

    @Builder
    public TeamModifyForm(Long id, League league, @NotBlank String name) {
        this.id = id;
        this.league = league;
        this.name = name;
    }
}
