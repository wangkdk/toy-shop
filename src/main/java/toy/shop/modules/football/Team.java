package toy.shop.modules.football;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "LEAGUE_NAME_UNIQUE",
                columnNames = {"league", "name"}
        )
})
@Entity
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private League league;

    // @Column(unique = true) 잘 안쓰인다. 이름이 랜덤으로 만들어주기 때문에
    @Column(nullable = false)
    private String name;

    @Builder
    public Team(League league, String name) {
        this.league = league;
        this.name = name;
    }

}
