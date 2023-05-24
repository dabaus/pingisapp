package se.omegapoint.pingpongapp.api.dto;

import jakarta.validation.constraints.NotNull;
import se.omegapoint.pingpongapp.api.entity.GameType;

public record CreateMatchRequest (@NotNull GameType gameType) {
}
