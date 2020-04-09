package toy.shop.modules.football.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import toy.shop.modules.football.entity.League;

@Data
public class TeamListDto {

    private Long id;
    private String name;
    private League league;

    public TeamListDto(){
    }

    @QueryProjection
    public TeamListDto(Long id, String name, League league) {
        this.id = id;
        this.name = name;
        this.league = league;
    }

}
