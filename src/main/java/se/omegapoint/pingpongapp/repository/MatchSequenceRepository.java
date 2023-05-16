package se.omegapoint.pingpongapp.repository;

import org.springframework.data.repository.CrudRepository;
import se.omegapoint.pingpongapp.entity.Match;
import se.omegapoint.pingpongapp.entity.MatchSequence;

import java.util.UUID;

public interface MatchSequenceRepository extends CrudRepository<MatchSequence, Long> {
}
