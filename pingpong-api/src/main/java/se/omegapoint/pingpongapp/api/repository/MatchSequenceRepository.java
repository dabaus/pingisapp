package se.omegapoint.pingpongapp.api.repository;

import org.springframework.data.repository.CrudRepository;
import se.omegapoint.pingpongapp.api.entity.MatchSequence;

import java.util.UUID;

public interface MatchSequenceRepository extends CrudRepository<MatchSequence, Long> {
}
