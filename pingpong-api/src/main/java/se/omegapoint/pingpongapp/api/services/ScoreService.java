package se.omegapoint.pingpongapp.api.services;

import se.omegapoint.pingpongapp.api.biz.HighScoreEntry;
import se.omegapoint.pingpongapp.api.entity.Match;
import se.omegapoint.pingpongapp.api.entity.Player;
import se.omegapoint.pingpongapp.api.entity.ScoreEntry;

import java.util.List;
import java.util.UUID;

public interface ScoreService {

    public ScoreEntry CreateScoreEntry(Match match, Player player, int score);
    public ScoreEntry FindScoreEntry(UUID id);
    public Iterable<ScoreEntry> ListAllScoreEntries();
    public void TriggerHighScoreComputation();
    public List<HighScoreEntry>  ListHighScore();
}
