package team5.todo.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import team5.todo.fixture.FixtureFactory;

public class HistoryIntegrationTest extends Integration {

	@Test
	void deleteTest() throws Exception {
		mockMvc.perform(delete("/history"))
			.andExpect(status().isOk())
			.andDo(print());
		mockMvc.perform(get("/history"))
			.andExpect(jsonPath("$[*]").isEmpty());
	}

	@Test
	void getTest() throws Exception {
		mockMvc.perform(get("/history"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[*].title").exists())
			.andExpect(jsonPath("$[*].origin").exists())
			.andExpect(jsonPath("$[*].destination").exists())
			.andExpect(jsonPath("$[*].at").exists())
			.andExpect(jsonPath("$[*].action").exists())
			.andExpect(jsonPath("$[*].createdAt").exists())
			.andDo(print());
	}

	@Test
	void historyAfterSaveTest() throws Exception {
		// given
		Thread.sleep(1000L);
		카드를_저장한다();

		// when
		mockMvc.perform(get("/history"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].action").value("생성"));
	}

	@Test
	void historyAfterDeleteTest() throws Exception {
		// given
		Thread.sleep(1000L);
		카드를_삭제한다();

		// when
		mockMvc.perform(get("/history"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].action").value("삭제"));
	}

	@Test
	void historyAfterModifyTest() throws Exception {
		// given
		Thread.sleep(1000L);
		카드를_수정한다();

		// when
		mockMvc.perform(get("/history"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].action").value("수정"));
	}

	@Test
	void historyAfterMoveTest() throws Exception {
		// given
		Thread.sleep(1000L);
		카드를_이동한다();

		// when
		mockMvc.perform(get("/history"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].action").value("이동"));
	}

	private void 카드를_저장한다() throws Exception {
		mockMvc.perform(
				post("/card")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(FixtureFactory.createCardSaveRequest())))
			.andExpect(status().isOk())
			.andDo(print());
	}

	private void 카드를_삭제한다() throws Exception {
		mockMvc.perform(
				delete("/card/3"))
			.andExpect(status().isOk())
			.andDo(print());
	}

	private void 카드를_수정한다() throws Exception {
		mockMvc.perform(
				put("/card/modify")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(FixtureFactory.createCardModifyRequest())))
			.andExpect(status().isOk())
			.andDo(print());
	}

	private void 카드를_이동한다() throws Exception {
		mockMvc.perform(
				put("/card/move")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(FixtureFactory.createCardMoveRequest())))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
