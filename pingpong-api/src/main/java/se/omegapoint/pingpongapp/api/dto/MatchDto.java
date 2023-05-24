package se.omegapoint.pingpongapp.api.dto;

import se.omegapoint.pingpongapp.api.entity.GameType;

import java.util.UUID;

public record MatchDto (UUID id, GameType type, long matchNo) {
}
