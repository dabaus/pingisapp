package se.omegapoint.pingpongapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.omegapoint.pingpongapp.api.dto.ScoreCreatedResponse;
import se.omegapoint.pingpongapp.api.biz.HighScoreEntry;
import se.omegapoint.pingpongapp.api.dto.HighScoreDto;
import se.omegapoint.pingpongapp.api.dto.CreateScoreRequest;
import se.omegapoint.pingpongapp.api.services.*;

import java.util.List;


@RestController
public class ScoreController {

    private ScoreService scoreService;
    private PlayerService playerService;
    private MatchService matchService;
    public ScoreController(ScoreService scoreService, PlayerService playerService, MatchService matchService) {
        this.scoreService = scoreService;
        this.playerService = playerService;
        this.matchService = matchService;
    }

    @GetMapping("/highscore")
    public ResponseEntity<List<HighScoreDto>> ListHighScore() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.scoreService.ListHighScore().stream().map(HighScoreEntry::toDto).toList());
    }

    @PostMapping("/score")
    public ResponseEntity<ScoreCreatedResponse> CreateScore(@Validated @RequestBody CreateScoreRequest request) {

        var player = playerService.FindPlayer(request.playerId());
        if(player == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        }

        var match = matchService.findMatchById(request.matchId());
        if(match == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }

        var scoreEntry = scoreService.CreateScoreEntry(match, player, request.score());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ScoreCreatedResponse(scoreEntry.getId()));
    }


}
