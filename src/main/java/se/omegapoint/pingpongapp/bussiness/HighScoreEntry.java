package se.omegapoint.pingpongapp.bussiness;

import se.omegapoint.pingpongapp.entity.Player;

public class HighScoreEntry {

    public Player player;
    public int score;

    public HighScoreEntry(Player player, int score) {
        this.player = player;
        this.score = score;
    }
}
