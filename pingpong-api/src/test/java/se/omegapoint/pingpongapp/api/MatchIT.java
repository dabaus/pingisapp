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
import org.junit.Assert;
import se.omegapoint.pingpongapp.api.dto.CreateMatchRequest;
import se.omegapoint.pingpongapp.api.dto.MatchDto;
import se.omegapoint.pingpongapp.api.entity.GameType;
import se.omegapoint.pingpongapp.api.services.MatchService;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class MatchIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MatchService matchService;

    @Autowired
    private ObjectMapper jsonMapper;

    @Test
    void createMatch() throws Exception {

        var content = jsonMapper.writeValueAsString(new CreateMatchRequest(GameType.KING));

        mvc.perform(post("/match")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    void getMatch() throws Exception {

        var match =  matchService.createMatch(GameType.KONOCK_OUT);
        var content = jsonMapper.writeValueAsString(match.toDto());

        mvc.perform(get(String.format("/match/%s", match.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(content));
    }

    @Test
    void listMatches() throws Exception {
        var match = matchService.createMatch(GameType.KING);

        var result = mvc.perform(get("/match")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        var content = result.getResponse().getContentAsString();

        var matchInList = Arrays.stream(jsonMapper.readValue(content, MatchDto[].class))
                .filter(x -> x.id().equals(match.getId()))
                .findFirst()
                .orElse(null);

        assertNotNull(matchInList);
        assertEquals(matchInList.id(), match.getId());
        assertEquals(matchInList.matchNo(), (long)match.getMatchNo());
        assertEquals(matchInList.gameType(), match.getGameType());

    }

}


