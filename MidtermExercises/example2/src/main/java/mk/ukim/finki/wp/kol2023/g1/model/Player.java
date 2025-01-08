package mk.ukim.finki.wp.kol2023.g1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String bio;

    private Double pointsPerGame;

    private PlayerPosition position;

    @ManyToOne
    private Team team;

    private Integer votes = 0;

    public Player(String name, String bio, Double pointsPerGame, PlayerPosition position, Team team) {
        this.name = name;
        this.bio = bio;
        this.pointsPerGame = pointsPerGame;
        this.position = position;
        this.team = team;
        this.votes = 0;
    }
}
