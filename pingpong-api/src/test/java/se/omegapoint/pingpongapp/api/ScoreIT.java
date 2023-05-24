package se.omegapoint.pingpongapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import se.omegapoint.pingpongapp.api.dto.CreateScoreRequest;
import se.omegapoint.pingpongapp.api.entity.GameType;
import se.omegapoint.pingpongapp.api.services.MatchService;
import se.omegapoint.pingpongapp.api.services.PlayerService;
import se.omegapoint.pingpongapp.api.services.ScoreService;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class ScoreIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private ObjectMapper jsonMapper;

    @Test
    void createScore() throws Exception {

        var player = playerService.createPlayer("Fiona");
        var match = matchService.createMatch(GameType.STRESSKING);

        var content = jsonMapper.writeValueAsString(new CreateScoreRequest(player.getId(), match.getId(), 0));

        mvc.perform(post("/score")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    void getScore() throws Exception {

        var player = playerService.createPlayer("Fiona");
        var match = matchService.createMatch(GameType.STRESSKING);
        var score = scoreService.CreateScoreEntry(match,player,10);

        mvc.perform(get(String.format("/score/%s",score.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.score", is(10)))
                .andExpect(jsonPath("$.playerId", is(player.getId().toString())))
                .andExpect(jsonPath("$.matchId", is(match.getId().toString())));
    }

    @Test
    void  listScore() throws Exception {

        var player = playerService.createPlayer("Fiona");
        var match = matchService.createMatch(GameType.STRESSKING);
        var score = scoreService.CreateScoreEntry(match,player,10);

        mvc.perform(get("/score")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].playerId", is(player.getId())));
    }
}