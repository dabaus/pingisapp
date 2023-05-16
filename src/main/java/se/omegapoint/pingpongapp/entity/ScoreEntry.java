package se.omegapoint.pingpongapp.entity;

import jakarta.persistence.*;
import org.hibernate.id.GUIDGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class ScoreEntry {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="score_id")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name="match_id")
    private Match match;

    @ManyToOne()
    @JoinColumn(name="player_id")
    private Player player;

    @Column(name="score", nullable = false)
    private int score;

    public ScoreEntry() {
    }

    public ScoreEntry(Match match, Player player, int score) {
        this.match = match;
        this.player = player;
        this.score = score;
    }

    public Player getPlayer() {
        return this.player;
    }

    public  Match getMatch() {
        return this.match;
    }

    public int getScore() {
        return this.score;
    }

}
