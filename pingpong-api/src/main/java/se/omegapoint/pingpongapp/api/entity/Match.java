package se.omegapoint.pingpongapp.api.entity;
import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import se.omegapoint.pingpongapp.api.dto.MatchDto;

import java.util.UUID;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="match_id")
    private UUID id;

    @OneToOne()
    @JoinColumn(name="match_no", nullable = false)
    private MatchSequence matchNo;

    @Column(name="game_type", nullable = false)
    private GameType gameType;

    public Match() {}


    public Match(GameType gameType, MatchSequence sequence) {
        this.gameType = gameType;
        this.matchNo = sequence;
    }

    public UUID getId() {
        return id;
    }

    public GameType getGameType() {
        return gameType;
    }

    public Long getMatchNo() {
        return matchNo.nubmer;
    }

    public MatchDto toDto() {
        return new MatchDto(this.id, this.gameType, this.matchNo.nubmer);
    }

}
