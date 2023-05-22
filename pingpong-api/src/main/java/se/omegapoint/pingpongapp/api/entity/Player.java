package se.omegapoint.pingpongapp.api.entity;

import jakarta.persistence.*;

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

    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
