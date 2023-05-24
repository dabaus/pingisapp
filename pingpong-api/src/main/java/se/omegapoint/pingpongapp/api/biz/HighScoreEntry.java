package se.omegapoint.pingpongapp.api.biz;

import se.omegapoint.pingpongapp.api.dto.HighScoreDto;
import se.omegapoint.pingpongapp.api.entity.Player;

public class HighScoreEntry {

    private Player player;
    private int score;

    public HighScoreEntry(Player player, int score) {
        this.player = player;
        this.score = score;
    }
    public HighScoreDto toDto()  {
       return new HighScoreDto(player.toDto(), score);
    }
}