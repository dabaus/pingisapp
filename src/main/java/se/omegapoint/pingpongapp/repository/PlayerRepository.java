package se.omegapoint.pingpongapp.repository;

import org.springframework.data.repository.CrudRepository;
import se.omegapoint.pingpongapp.entity.Player;

import java.util.UUID;

public interface PlayerRepository  extends CrudRepository<Player, UUID> {
}
