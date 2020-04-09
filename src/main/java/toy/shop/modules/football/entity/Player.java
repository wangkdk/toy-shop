package toy.shop.modules.football.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name", "number"})
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    // DB 에 만들어지는 column 명이 team_id 가 되며, 이 fk 로 join 활용
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Player(String name, Integer number, Team team) {
        this.name = name;
        this.number = number;
        this.team = team;
    }
}
