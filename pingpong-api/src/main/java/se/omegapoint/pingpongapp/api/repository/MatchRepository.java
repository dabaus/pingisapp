package se.omegapoint.pingpongapp.api.repository;

import org.springframework.data.repository.CrudRepository;
import se.omegapoint.pingpongapp.api.entity.Match;
import java.util.UUID;

public interface MatchRepository extends CrudRepository<Match, UUID> {
}
