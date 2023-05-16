package se.omegapoint.pingpongapp.services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import se.omegapoint.pingpongapp.entity.*;

public interface MatchService {

    public Match createMatch(GameType type);
    public Match findMatchById(UUID id);
    public Iterable<Match> listAllMatches();

}
