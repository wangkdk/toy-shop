package toy.shop.modules.football;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
