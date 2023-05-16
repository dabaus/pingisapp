package se.omegapoint.pingpongapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.omegapoint.pingpongapp.bussiness.HighScoreEntry;
import se.omegapoint.pingpongapp.services.MatchService;
import se.omegapoint.pingpongapp.services.PlayerService;
import se.omegapoint.pingpongapp.services.ScoreService;

import java.util.List;


@RestController
public class ScoreController {

    private ScoreService scoreService;
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/score/high")
    public List<HighScoreEntry> ListHighScore() {
        return this.scoreService.ListHighScore();
    }
}
