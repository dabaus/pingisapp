package se.omegapoint.pingpongapp.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import se.omegapoint.pingpongapp.repository.PlayerRepository;

import java.util.UUID;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="player_id")
    private UUID id;
    @Column(name="name", nullable = false)
    private String name;

    public Player() {
    }
    public Player(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public UUID getId() { return id;}
}
