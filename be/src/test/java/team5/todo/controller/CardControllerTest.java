package team5.todo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import team5.todo.annotation.ControllerTest;
import team5.todo.controller.dto.CardModifyRequest;
import team5.todo.controller.dto.CardMoveRequest;
import team5.todo.controller.dto.CardSaveRequest;
import team5.todo.service.CardService;
import team5.todo.service.HistoryService;

@ControllerTest(CardController.class)
public class CardControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CardService cardService;
	@MockBean
	private HistoryService historyService;
	private ObjectMapper objectMapper = new ObjectMapper();

	@DisplayName("카드의 생성이 성공하면 200코드를 반환한다")
	@Test
	void createSuccessTest() throws Exception {

		CardSaveRequest cardSaveRequest = CardSaveRequest.builder()
			.categoryId(1L)
			.title("제목")
			.contents("내용")
			.build();

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
			.post("/card")
			.content(objectMapper.writeValueAsString(cardSaveRequest))
			.contentType(MediaType.APPLICATION_JSON));

		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}

	@DisplayName("카드의 삭제가 성공하면 200코드를 반환한다")
	@Test
	void deleteSuccessTest() throws Exception {

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
			.delete("/card/{id}", 1L)
			.contentType(MediaType.APPLICATION_JSON));

		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}

	@DisplayName("카드의 수정이 성공하면 200코드를 반환한다")
	@Test
	void modifySuccessTest() throws Exception {

		CardModifyRequest cardModifyRequest = CardModifyRequest.builder()
			.id(1L)
			.title("바뀐 제목")
			.contents("바뀐 내용")
			.build();

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
			.put("/card/modify")
			.content(objectMapper.writeValueAsString(cardModifyRequest))
			.contentType(MediaType.APPLICATION_JSON));

		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}

	@DisplayName("카드의 이동이 성공하면 200코드를 반환한다")
	@Test
	void moveSuccessTest() throws Exception {

		CardMoveRequest cardMoveRequest = CardMoveRequest.builder()
			.id(1L)
			.categoryId(3L)
			.beforeCardId(5L)
			.afterCardId(4L)
			.build();

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
			.put("/card/move")
			.content(objectMapper.writeValueAsString(cardMoveRequest))
			.contentType(MediaType.APPLICATION_JSON));

		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@DisplayName("제목이 64자 이상일 시 BAD REQUEST를 반환한다")
	void throwBadRequestWhenTitleOverlength() throws Exception{
		CardSaveRequest cardSaveRequest = CardSaveRequest.builder()
				.categoryId(1L)
				.title("1234567890123456789012345678901234567890123456789012345678901234567890")
				.contents("contents")
				.build();
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
				.post("/card")
				.content(objectMapper.writeValueAsString(cardSaveRequest))
				.contentType(MediaType.APPLICATION_JSON));
		resultActions
				.andExpect(status().isBadRequest())
				.andDo(print());
	}

	@Test
	@DisplayName("내용이 1자 이하일 시 BAD REQUEST를 반환한다")
	void throwBadRequestWhenContentsUnderlength() throws Exception{
		CardModifyRequest cardModifyRequest = CardModifyRequest.builder()
				.id(1L)
				.title("title")
				.contents("")
				.build();
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
				.put("/card/modify")
				.content(objectMapper.writeValueAsString(cardModifyRequest))
				.contentType(MediaType.APPLICATION_JSON));
		resultActions
				.andExpect(status().isBadRequest())
				.andDo(print());
	}

	@Test
	@DisplayName("id값이 null일 시 BAD REQUEST를 반환한다")
	void throwBadRequestNoId() throws Exception{
		CardMoveRequest cardMoveRequest = CardMoveRequest.builder()
				.id(1L)
				.afterCardId(null)
				.beforeCardId(null)
				.categoryId(null)
				.build();
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
				.put("/card/move")
				.content(objectMapper.writeValueAsString(cardMoveRequest))
				.contentType(MediaType.APPLICATION_JSON));
		resultActions
				.andExpect(status().isBadRequest())
				.andDo(print());
	}
}
