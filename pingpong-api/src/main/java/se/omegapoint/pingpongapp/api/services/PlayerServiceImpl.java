package se.omegapoint.pingpongapp.api.services;

import org.springframework.stereotype.Service;
import se.omegapoint.pingpongapp.api.entity.Player;
import se.omegapoint.pingpongapp.api.repository.PlayerRepository;

import java.util.UUID;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository repo;
    public PlayerServiceImpl(PlayerRepository repo) {
        this.repo = repo;
    }

    public Player createPlayer(String name) {
        return repo.save(new Player(name));
    }

    public UUID store(Player player) {
        return repo.save(player).getId();
    }

    public Iterable<Player> listAllPlayers() {
        return repo.findAll();
    }

    public Player FindPlayer(UUID id) {
        return repo.findById(id).orElse(null);
    }
}
