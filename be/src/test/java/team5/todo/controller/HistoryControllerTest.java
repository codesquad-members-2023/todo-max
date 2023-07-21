package team5.todo.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import team5.todo.annotation.ControllerTest;
import team5.todo.controller.dto.HistoryResponse;
import team5.todo.service.HistoryService;

@ControllerTest(HistoryController.class)
class HistoryControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private HistoryService historyService;

	@DisplayName("전체 조회를 했을 때 모든 기록을 반환한다.")
	@Test
	void getHistories() throws Exception {
		given(historyService.getHistories()).willReturn(createDummyHistoryResponses());

		ResultActions resultActions = mockMvc.perform(get("/history"));

		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(4))
			.andExpect(jsonPath("$.[0].title").value("제목1"))
			.andExpect(jsonPath("$.[1].action").value("삭제"))
			.andExpect(jsonPath("$.[2].origin").value("해야 할 일"))
			.andExpect(jsonPath("$.[3].action").value("수정"))
			.andDo(print());
	}

	@Test
	@DisplayName("전체 삭제 시 모든 기록을 삭제하고 200ok를 반환한다")
	void deleteHistories() throws Exception {
		ResultActions resultActions = mockMvc.perform(delete("/history"));

		resultActions
			.andExpect(status().isOk()).andDo(print());
	}

	private List<HistoryResponse> createDummyHistoryResponses() {
		HistoryResponse historyResponse1 = HistoryResponse.builder()
			.title("제목1")
			.action("생성")
			.at("해야 할 일")
			.createdAt(LocalDateTime.now())
			.build();
		HistoryResponse historyResponse2 = HistoryResponse.builder()
			.title("제목2")
			.action("삭제")
			.at("해야 할 일")
			.createdAt(LocalDateTime.now())
			.build();
		HistoryResponse historyResponse3 = HistoryResponse.builder()
			.title("제목3")
			.origin("해야 할 일")
			.destination("완료한 일")
			.action("이동")
			.createdAt(LocalDateTime.now())
			.build();
		HistoryResponse historyResponse4 = HistoryResponse.builder()
			.title("제목4")
			.action("수정")
			.createdAt(LocalDateTime.now())
			.build();

		return List.of(historyResponse1, historyResponse2, historyResponse3, historyResponse4);
	}
}
