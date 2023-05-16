package se.omegapoint.pingpongapp.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;

@Entity
public class MatchSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_seq")
    @Column(name="seq_no", nullable = false)
    public Long nubmer;

}
