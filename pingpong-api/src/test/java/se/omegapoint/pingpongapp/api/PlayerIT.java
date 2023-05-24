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
import se.omegapoint.pingpongapp.api.dto.CreateMatchRequest;
import se.omegapoint.pingpongapp.api.dto.CreatePlayerRequest;
import se.omegapoint.pingpongapp.api.entity.GameType;
import se.omegapoint.pingpongapp.api.services.PlayerService;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
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
class PlayerIT {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private ObjectMapper jsonMapper;


	@Test
	void createPlayer() throws Exception {
		var content = jsonMapper.writeValueAsString(new CreatePlayerRequest("Jakob"));

		mvc.perform(post("/player")
						.content(content)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", notNullValue()));
	}

	@Test
	void getPlayer() throws Exception {

		var player = playerService.createPlayer("Joe");
		var content = jsonMapper.writeValueAsString(player.toDto());

		mvc.perform(get(String.format("/player/%s", player.getId()))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(content));
	}

	@Test
	void listPlayers() throws  Exception {

		playerService.createPlayer("Max");

		mvc.perform(get(String.format("/player"))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("Max")))
				.andExpect(jsonPath("$[0].id", notNullValue()));
	}
}
