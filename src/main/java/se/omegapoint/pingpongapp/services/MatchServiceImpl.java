package se.omegapoint.pingpongapp.services;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.omegapoint.pingpongapp.entity.GameType;
import se.omegapoint.pingpongapp.entity.Match;
import se.omegapoint.pingpongapp.entity.MatchSequence;
import se.omegapoint.pingpongapp.repository.MatchRepository;
import se.omegapoint.pingpongapp.repository.MatchSequenceRepository;

import java.util.UUID;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository repo;
    private MatchSequenceRepository seqRep;

    public MatchServiceImpl(MatchRepository repo, MatchSequenceRepository seqRep) {
        this.repo = repo;
        this.seqRep = seqRep;
    }

    @Transactional
    public Match createMatch(GameType type) {
        var seq = seqRep.save(new MatchSequence());
        var match = new Match(type, seq);
        return repo.save(match);
    }
    public Match findMatchById(UUID id) {
        return repo.findById(id).orElse(null);
    }
    public Iterable<Match> listAllMatches() {
        return repo.findAll();
    }
}
