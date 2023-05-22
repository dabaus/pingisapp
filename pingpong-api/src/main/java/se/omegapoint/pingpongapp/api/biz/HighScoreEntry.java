package se.omegapoint.pingpongapp.api.biz;

import se.omegapoint.pingpongapp.api.entity.Player;

public class HighScoreEntry {

    public Player player;
    public int score;

    public HighScoreEntry(Player player, int score) {
        this.player = player;
        this.score = score;
    }
}
