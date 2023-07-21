package team5.todo.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import team5.todo.annotation.ServiceTest;
import team5.todo.controller.dto.HistoryResponse;
import team5.todo.domain.History;
import team5.todo.repository.HistoryRepository;

@ServiceTest
class HistoryServiceTest {

	@InjectMocks
	private HistoryService historyService;
	@Mock
	private HistoryRepository historyRepository;

	@Test
	@DisplayName("레포지토리에서 History를 가져와서 HistoryResponse를 만든다.")
	void getHistoriesTest() {
		//given
		given(historyRepository.findAll()).willReturn(createDummyHistories());

		//when
		List<HistoryResponse> actual = historyService.getHistories();

		//then
		assertThat(actual).isInstanceOf(List.class);
		assertThat(actual.size()).isEqualTo(4);
	}

	private List<History> createDummyHistories() {
		History history1 = History.builder()
			.title("제목1")
			.action("생성")
			.at("해야 할 일")
			.createdAt(LocalDateTime.now())
			.build();
		History history2 = History.builder()
			.title("제목2")
			.action("삭제")
			.at("해야 할 일")
			.createdAt(LocalDateTime.now())
			.build();
		History history3 = History.builder()
			.title("제목3")
			.origin("해야 할 일")
			.destination("완료한 일")
			.action("이동")
			.createdAt(LocalDateTime.now())
			.build();
		History history4 = History.builder()
			.title("제목4")
			.action("수정")
			.createdAt(LocalDateTime.now())
			.build();

		return List.of(history1, history2, history3, history4);
	}
}
