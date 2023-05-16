package se.omegapoint.pingpongapp.services;

import se.omegapoint.pingpongapp.entity.Match;
import se.omegapoint.pingpongapp.entity.Player;
import se.omegapoint.pingpongapp.entity.ScoreEntry;

import java.util.List;

public interface ScoreService {

    public ScoreEntry CreateScoreEntry(Match match, Player player, int score);
    public List<ScoreEntry> ListHighScore();
}
