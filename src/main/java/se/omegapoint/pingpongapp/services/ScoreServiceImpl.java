package se.omegapoint.pingpongapp.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import se.omegapoint.pingpongapp.bussiness.HighScoreEntry;
import se.omegapoint.pingpongapp.entity.Match;
import se.omegapoint.pingpongapp.entity.Player;
import se.omegapoint.pingpongapp.entity.ScoreEntry;
import se.omegapoint.pingpongapp.repository.ScoreRepository;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

@Service
public class ScoreServiceImpl implements ScoreService {

    private ScoreRepository repo;
    private SessionFactory sessionFactory;
    public ScoreServiceImpl(ScoreRepository repo,
                            SessionFactory sessionFactory) {
        this.repo = repo;
        this.sessionFactory = sessionFactory;
    }
    public ScoreEntry CreateScoreEntry(Match match,Player player, int score) {
        return this.repo.save(new ScoreEntry(match, player, score));
    }
    public List<HighScoreEntry> ListHighScore() {
        var entires = new ArrayList<HighScoreEntry>();
        var session = sessionFactory.openSession();
        var query = session.createQuery("""
SELECT p.id, p.name, SUM(e.score) FROM ScoreEntry e 
INNER JOIN Player p ON p.id = e.player 
GROUP BY p.id, p.name
ORDER BY SUM(e.score) DESC
""");

        List<Object[]> rows = query.list();
        for (var r :rows) {
            var player = new Player();
            player.setId(UUID.fromString(r[0].toString()));
            player.setName(r[1].toString());
            entires.add(new HighScoreEntry(player, Integer.parseInt(r[2].toString())));
        }
        return entires;
    }
}
