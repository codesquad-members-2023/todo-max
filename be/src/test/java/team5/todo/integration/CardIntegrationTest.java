package team5.todo.integration;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;

import team5.todo.annotation.IntegrationTest;
import team5.todo.domain.Card;
import team5.todo.fixture.FixtureFactory;
import team5.todo.repository.CardRepository;

@IntegrationTest
public class CardIntegrationTest extends Integration {

	@Autowired
	private CardRepository cardRepository;

	@Test
	void saveTest() throws Exception {
		mockMvc.perform(
				post("/card")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(FixtureFactory.createCardSaveRequest())))
			.andExpect(status().isOk())
			.andDo(print());

		assertThat(cardRepository.findById(11L)).isNotNull();
	}

	@Test
	void deleteTest() throws Exception {
		mockMvc.perform(
				delete("/card/3"))
			.andExpect(status().isOk())
			.andDo(print());

		assertThatThrownBy(() -> cardRepository.findById(3L))
			.isInstanceOf(DataAccessException.class);
	}

	@Test
	void modifyTest() throws Exception {
		mockMvc.perform(
				put("/card/modify")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(FixtureFactory.createCardModifyRequest())))
			.andExpect(status().isOk())
			.andDo(print());

		Card card = cardRepository.findById(3L);
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(card.getTitle()).isEqualTo("브루니랑 놀기");
			softAssertions.assertThat(card.getContents()).isEqualTo("코쿼에서 밥먹기");
		});
	}

	@Test
	void moveTest() throws Exception {
		mockMvc.perform(
				put("/card/move")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(FixtureFactory.createCardMoveRequest())))
			.andExpect(status().isOk())
			.andDo(print());

		assertThat(cardRepository.findById(1L).getPosition()).isEqualTo(1500D);
	}
}
