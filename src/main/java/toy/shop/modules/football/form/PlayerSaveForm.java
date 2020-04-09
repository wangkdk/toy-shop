package toy.shop.modules.football.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toy.shop.modules.football.Team;

@Getter
@Setter
@NoArgsConstructor
public class PlayerSaveForm {
    private String name;
    private Integer number;
    private Long teamId;
}
