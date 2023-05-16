package se.omegapoint.pingpongapp.repository;

import org.springframework.data.repository.CrudRepository;
import se.omegapoint.pingpongapp.entity.Match;
import java.util.UUID;

public interface MatchRepository extends CrudRepository<Match, UUID> {
}
