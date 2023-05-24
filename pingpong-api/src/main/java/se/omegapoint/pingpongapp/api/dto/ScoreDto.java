package se.omegapoint.pingpongapp.api.dto;

import java.util.UUID;

public record ScoreDto(UUID playerId, UUID matchId, int score) {
}
