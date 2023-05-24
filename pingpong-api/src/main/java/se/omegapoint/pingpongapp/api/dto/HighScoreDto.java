package se.omegapoint.pingpongapp.api.dto;

import se.omegapoint.pingpongapp.api.biz.HighScoreEntry;
import se.omegapoint.pingpongapp.api.entity.Player;

import java.util.UUID;

public record HighScoreDto (PlayerDto player, int score) {
}


