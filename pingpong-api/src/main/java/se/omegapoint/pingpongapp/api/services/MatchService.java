package se.omegapoint.pingpongapp.api.services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import se.omegapoint.pingpongapp.api.entity.*;

public interface MatchService {

    public Match createMatch(GameType type);
    public Match findMatchById(UUID id);
    public Iterable<Match> listAllMatches();

}
