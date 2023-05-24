package se.omegapoint.pingpongapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.omegapoint.pingpongapp.api.dto.MatchCreatedResponse;
import se.omegapoint.pingpongapp.api.dto.MatchDto;
import se.omegapoint.pingpongapp.api.dto.CreateMatchRequest;
import se.omegapoint.pingpongapp.api.services.MatchService;

import java.util.UUID;

@RestController
public class MatchController {

    private MatchService matchService;
    public MatchController(MatchService service) {
        this.matchService = service;
    }

    @PostMapping("/match")
    public ResponseEntity<MatchCreatedResponse> CreateMatch(@RequestBody CreateMatchRequest request) {
        var match = matchService.createMatch(request.gameType());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MatchCreatedResponse(match.getId()));
    }

    @GetMapping("/match")
    public ResponseEntity<MatchDto> GetMatch(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(matchService.findMatchById(id).toMatchDto());
    }
}
