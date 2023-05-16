package se.omegapoint.pingpongapp.repository;

import org.springframework.data.repository.CrudRepository;
import se.omegapoint.pingpongapp.entity.ScoreEntry;

import java.util.UUID;

public interface ScoreRepository extends CrudRepository<ScoreEntry, UUID>  {
}
