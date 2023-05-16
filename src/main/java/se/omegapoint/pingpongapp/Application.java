package se.omegapoint.pingpongapp;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import se.omegapoint.pingpongapp.bussiness.HighScoreEntry;
import se.omegapoint.pingpongapp.entity.*;
import se.omegapoint.pingpongapp.repository.MatchRepository;
import se.omegapoint.pingpongapp.repository.MatchSequenceRepository;
import se.omegapoint.pingpongapp.repository.PlayerRepository;
import se.omegapoint.pingpongapp.repository.ScoreRepository;
import se.omegapoint.pingpongapp.services.MatchService;
import se.omegapoint.pingpongapp.services.PlayerService;
import se.omegapoint.pingpongapp.services.ScoreService;

@EnableTransactionManagement
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Bean
	public CommandLineRunner demo(PlayerService playerService,
								  MatchService matchService,
								  ScoreService scoreService) {
		return (args) -> {
			var p1 = playerService.createPlayer("Olaf");
			var p2 = playerService.createPlayer("Joan");
			var p3 = playerService.createPlayer("Hellen");

			var match1 = matchService.createMatch(GameType.KING);
			var match2 = matchService.createMatch(GameType.KONOCK_OUT);

			scoreService.CreateScoreEntry(match1, p1, 1);
			scoreService.CreateScoreEntry(match1, p2, 3);
			scoreService.CreateScoreEntry(match1, p3, 2);

			scoreService.CreateScoreEntry(match2, p1, 0);
			scoreService.CreateScoreEntry(match2, p2, 1);
			scoreService.CreateScoreEntry(match2, p3, 0);

			System.out.println("HIGHSCORE:");
			for (HighScoreEntry e: scoreService.ListHighScore()) {
				var playerName = e.player.getName();
				var score = e.score;
				System.out.println("\tPlayer: %s, Score: %d".formatted(playerName,score));
			}
		};
	}
}
