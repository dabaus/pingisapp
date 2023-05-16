package se.omegapoint.pingpongapp.services;

import se.omegapoint.pingpongapp.entity.Player;

import java.util.UUID;

public interface PlayerService {
    public Player createPlayer(String name);
    public UUID store(Player player);
    public Iterable<Player> listAllPlayers();
    public Player FindPlayer(UUID id);
}
