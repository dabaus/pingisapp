package se.omegapoint.pingpongapp.api.repository;

import org.springframework.data.repository.CrudRepository;
import se.omegapoint.pingpongapp.api.entity.ScoreEntry;

import java.util.UUID;

public interface ScoreRepository extends CrudRepository<ScoreEntry, UUID>  {
}
