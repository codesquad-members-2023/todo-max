package codesquad.todolist.travelers.history.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import codesquad.todolist.travelers.actionType.ActionType;
import codesquad.todolist.travelers.annotation.ServiceTest;
import codesquad.todolist.travelers.global.CustomException;
import codesquad.todolist.travelers.global.ErrorCode;
import codesquad.todolist.travelers.history.domain.dto.response.ActionHistoryResponseDto;
import codesquad.todolist.travelers.history.domain.vo.ActionHistory;
import codesquad.todolist.travelers.history.repository.HistoryRepository;
import codesquad.todolist.travelers.process.repository.ProcessRepository;
import codesquad.todolist.travelers.task.domain.entity.Task;
import codesquad.todolist.travelers.task.repository.TaskRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ServiceTest
class HistoryServiceTest {

    @InjectMocks
    HistoryService historyService;

    @Mock
    HistoryRepository historyRepository;

    @Mock
    TaskRepository taskRepository;

    @Mock
    ProcessRepository processRepository;

    @Test
    @DisplayName("deleteAllHistory 메서드를 통해 deleteAll 메서드를 호출할수 있다.")
    void deleteAllHistorySuccessTest() {
        //given
        doNothing().when(historyRepository).deleteAll();

        //when
        historyService.deleteAllHistory();

        //then
        verify(historyRepository).deleteAll();
    }

    @Test
    @DisplayName("getAllHistory 메서드를 통해 모든 활동기록을 가져올수 있다.")
    void getAllHistorySuccessTest() {
        //given
        List<ActionHistoryResponseDto> expected = dummyActionHistoryResponseDtoAsList();
        given(historyRepository.findAll()).willReturn(dummyActionHistoryAsList());

        //when
        List<ActionHistoryResponseDto> actual = historyService.getAllHistory();

        //then
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("createdTime")
                .isEqualTo(expected);
    }

    List<ActionHistory> dummyActionHistoryAsList() {
        return Arrays.asList(dummyActionHistoryForMove(), dummyActionHistoryForCreate());
    }

    List<ActionHistoryResponseDto> dummyActionHistoryResponseDtoAsList() {
        return Arrays.asList(dummyActionHistoryResponseDtoForMove(), dummyForActionHistoryResponseDtoForCreate());
    }

    ActionHistory dummyActionHistoryForMove() {
        return new ActionHistory("제목입니다", "해야할 일", "하기싫은 일", "이동", LocalDateTime.now(), "anonymous",
                "image.jpeg");
    }

    ActionHistory dummyActionHistoryForCreate() {
        return new ActionHistory("제목입니다", "해야할 일", "", "등록", LocalDateTime.now(), "anonymous",
                "image.jpeg");
    }

    ActionHistoryResponseDto dummyActionHistoryResponseDtoForMove() {
        return new ActionHistoryResponseDto("제목입니다", "해야할 일", "하기싫은 일", "이동", LocalDateTime.now(), "anonymous",
                "image.jpeg");
    }

    ActionHistoryResponseDto dummyForActionHistoryResponseDtoForCreate() {
        return new ActionHistoryResponseDto("제목입니다", "해야할 일", "", "등록", LocalDateTime.now(), "anonymous",
                "image.jpeg");
    }

    @Test
    @DisplayName("saveActionCreateHistory() 를 통해 task 등록 관련 활동기록을 저장할 수 있다.")
    void saveActionCreateHistorySuccessTest() {
        //given
        ActionType actionTypeForCreate = ActionType.CREATE_TASK;
        Task task = dummyTask();
        given(taskRepository.findByIgnoringDeleted(task.getTaskId())).willReturn(task);
        given(processRepository.findProcessNameBy(task.getProcessId())).willReturn("해야할 일");
        given(historyRepository.save(any())).willReturn(Optional.of(1L));

        //when & then
        Assertions.assertThatCode(() -> historyService.saveActionCreateHistory(task.getTaskId(), actionTypeForCreate))
                .doesNotThrowAnyException();

        //then
        verify(historyRepository).save(any());
    }

    Task dummyTask() {
        return new Task(1L, "제목입니다", "내용입니다", "web", 1L, 1L);
    }

    @Test
    @DisplayName("saveActionMoveHistory()를 통해 task 이동 관련 활동기록을 저장할 수 있다")
    void saveActionMoveHistorySuccessTest() {
        //given
        ActionType actionTypeForMove = ActionType.MOVE_TASK;
        String fromName = "하기 싫은 일";
        Task task = dummyTask();
        given(taskRepository.findByIgnoringDeleted(task.getTaskId())).willReturn(task);
        given(processRepository.findProcessNameBy(task.getProcessId())).willReturn("하기 싫은 일");
        given(historyRepository.save(any())).willReturn(Optional.of(1L));

        //when & then
        Assertions.assertThatCode(
                        () -> historyService.saveActionMoveHistory(task.getTaskId(), fromName, actionTypeForMove))
                .doesNotThrowAnyException();

        //then
        verify(historyRepository).save(any());
    }

    @Test
    @DisplayName("saveActionDeleteAndUpdateHistory()를 통해 task 삭제 관련 활동기록을 저장할 수 있다.")
    void saveActionUpdateHistorySuccessTest() {
        //given
        ActionType actionTypeForDelete = ActionType.DELETE_TASK;
        Task task = dummyTask();
        given(taskRepository.findByIgnoringDeleted(task.getTaskId())).willReturn(task);
        given(processRepository.findProcessNameBy(task.getProcessId())).willReturn("해야할 일");
        given(historyRepository.save(any())).willReturn(Optional.of(1L));

        //when & then
        Assertions.assertThatCode(
                        () -> historyService.saveActionDeleteAndUpdateHistory(task.getTaskId(), actionTypeForDelete))
                .doesNotThrowAnyException();

        //then
        verify(historyRepository).save(any());
    }

    @Test
    @DisplayName("saveActionDeleteAndUpdateHistory()를 통해 task 수정 관련 활동기록을 저장할 수 있다.")
    void saveActionUpdateSuccessTest() {
        ActionType actionTypeForDelete = ActionType.UPDATE_TASK;
        Task task = dummyTask();
        given(taskRepository.findByIgnoringDeleted(task.getTaskId())).willReturn(task);
        given(historyRepository.save(any())).willReturn(Optional.of(1L));

        //whe & then
        Assertions.assertThatCode(
                        () -> historyService.saveActionDeleteAndUpdateHistory(task.getTaskId(), actionTypeForDelete))
                .doesNotThrowAnyException();

        //then
        verify(historyRepository).save(any());
    }

    @Test
    @DisplayName("활동기록 저장에 실패하면 CustomeException이 발생한다.")
    void saveHistoryFailTest() {
        //given
        ActionType actionTypeForCreate = ActionType.CREATE_TASK;
        Task task = dummyTask();
        given(taskRepository.findByIgnoringDeleted(task.getTaskId())).willReturn(task);
        given(processRepository.findProcessNameBy(task.getProcessId())).willReturn("해야할 일");
        given(historyRepository.save(any())).willReturn(Optional.empty());

        //when & then
        Assertions.assertThatThrownBy(
                        () -> historyService.saveActionCreateHistory(task.getTaskId(), actionTypeForCreate))
                .isInstanceOfSatisfying(CustomException.class,
                        e -> Assertions.assertThat(e.getStatusCode().getMessage())
                                .isEqualTo(ErrorCode.FAIL_HISTORY_CREATE.getMessage()));
    }
}