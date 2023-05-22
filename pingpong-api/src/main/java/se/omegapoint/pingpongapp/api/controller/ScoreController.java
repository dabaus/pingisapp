package se.omegapoint.pingpongapp.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.omegapoint.pingpongapp.api.biz.HighScoreEntry;
import se.omegapoint.pingpongapp.api.services.*;

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
