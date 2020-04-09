package toy.shop.modules.football.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerSaveForm {
    private String name;
    private Integer number;
    private Long teamId;
}
