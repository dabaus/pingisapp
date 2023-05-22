package se.omegapoint.pingpongapp.api.repository;

import org.springframework.data.repository.CrudRepository;
import se.omegapoint.pingpongapp.api.entity.Player;

import java.util.UUID;

public interface PlayerRepository extends CrudRepository<Player, UUID> {
}
