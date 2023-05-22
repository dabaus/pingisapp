package se.omegapoint.pingpongapp.api.services;

import se.omegapoint.pingpongapp.api.entity.Player;

import java.util.UUID;

public interface PlayerService {
    public Player createPlayer(String name);
    public UUID store(Player player);
    public Iterable<Player> listAllPlayers();
    public Player FindPlayer(UUID id);
}
