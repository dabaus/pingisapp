package se.omegapoint.pingpongapp.services;

import org.springframework.stereotype.Service;
import se.omegapoint.pingpongapp.entity.Match;
import se.omegapoint.pingpongapp.entity.Player;
import se.omegapoint.pingpongapp.entity.ScoreEntry;
import se.omegapoint.pingpongapp.repository.ScoreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements  ScoreService {

    private ScoreRepository repo;
    public ScoreServiceImpl(ScoreRepository repo) {
        this.repo = repo;
    }
    public ScoreEntry CreateScoreEntry(Match match,Player player, int score) {
        return this.repo.save(new ScoreEntry(match, player, score));
    }
    public List<ScoreEntry> ListHighScore() {
        var entries = new ArrayList<ScoreEntry>();
        for (var e: repo.findAll()) {
            entries.add(e);
        }
        entries.sort((e1, e2) -> {
            if(e1.getScore() > e2.getScore()) {
                return 1;
            } else {
                return -1;
            }
        });
        return entries;
    }
}
