package se.omegapoint.pingpongapp.api.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
public record CreateScoreRequest(
        @NotNull UUID playerId,
        @Nonnull UUID matchId,
        @Min(0) int score) {
}
