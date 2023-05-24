package se.omegapoint.pingpongapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.omegapoint.pingpongapp.api.dto.PlayerCreatedResponse;
import se.omegapoint.pingpongapp.api.dto.CreatePlayerRequest;
import se.omegapoint.pingpongapp.api.dto.PlayerDto;
import se.omegapoint.pingpongapp.api.entity.Player;
import se.omegapoint.pingpongapp.api.services.PlayerService;
import se.omegapoint.pingpongapp.api.utils.StreamUtils;

import java.util.List;
import java.util.UUID;

@RestController
public class PlayerController {

    private PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @PostMapping("/player")
    public ResponseEntity<PlayerCreatedResponse> CreatePlayer(@RequestBody CreatePlayerRequest request) {
        var player = service.createPlayer(request.name());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PlayerCreatedResponse(player.getId()));
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<PlayerDto> GetPlayer(@PathVariable UUID id) {
        var player = service.FindPlayer(id);
        return ResponseEntity.status(HttpStatus.OK).body(player.toDto());
    }

    @GetMapping("/player")
    public ResponseEntity<List<PlayerDto>> GetPlayer() {
        var players = StreamUtils.asStream(service.listAllPlayers())
                .map(Player::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(players);
    }

}