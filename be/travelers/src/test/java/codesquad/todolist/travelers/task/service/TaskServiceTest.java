package codesquad.todolist.travelers.task.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import codesquad.todolist.travelers.annotation.ServiceTest;
import codesquad.todolist.travelers.global.CustomException;
import codesquad.todolist.travelers.process.domain.entity.Process;
import codesquad.todolist.travelers.process.repository.ProcessRepository;
import codesquad.todolist.travelers.task.domain.dto.request.TaskRequestDto;
import codesquad.todolist.travelers.task.domain.dto.response.TaskResponseDto;
import codesquad.todolist.travelers.task.domain.dto.response.TasksByProcessResponseDto;
import codesquad.todolist.travelers.task.domain.entity.Task;
import codesquad.todolist.travelers.task.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ServiceTest
class TaskServiceTest {

    @InjectMocks
    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @Mock
    ProcessRepository processRepository;

    // 등록
    @Test
    @DisplayName("createTask()를 통해 Task를 저장할수 있다.")
    void createTaskSuccessTest() {
        //given
        given(taskRepository.save(any())).willReturn(Optional.ofNullable(dummyTask().getTaskId()));

        //when
        taskService.createTask(dummyTaskRequestDto());

        //then
        Assertions.assertThat(1L).isEqualTo(dummyTask().getTaskId());
    }

    // 등록 예외 테스트
    @Test
    @DisplayName("할 일 저장에 실패하면 CustomException이 발생한다.")
    void createTaskSuccessThrowsCustomException() {
        // given
        given(taskRepository.save(any())).willReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> taskService.createTask(dummyTaskRequestDto())).isInstanceOf(CustomException.class);
    }

    private Task dummyTask() {
        return new Task(1L, "제목입니다", "내용입니다", "web", 1L, 0);
    }

    private TaskRequestDto dummyTaskRequestDto() {
        return new TaskRequestDto("제목입니다", "내용입니다", "web", 1L);
    }

    @Test
    @DisplayName("findProcesses()를 메서드를 통해 모든 process와 task를 반환할 수 있다.")
    void getAllTodoListSuccessTest() {
        given(processRepository.findProcesses()).willReturn(dummyProcessList());
        given(taskRepository.findAllBy(1L)).willReturn(dummyTaskListForProcessId1());
        given(taskRepository.findAllBy(2L)).willReturn(dummyTaskListForProcessId2());

        //when
        List<TasksByProcessResponseDto> actual = taskService.getAllTodoList();

        //then
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(dummyTasksByProcessResponseDtoList());
    }

    private List<TasksByProcessResponseDto> dummyTasksByProcessResponseDtoList() {
        List<Process> processes = dummyProcessList();
        List<TaskResponseDto> tasks1 = dummyTaskResponseDto(dummyTaskListForProcessId1());
        List<TaskResponseDto> tasks2 = dummyTaskResponseDto(dummyTaskListForProcessId2());

        return List.of(TasksByProcessResponseDto.fromEntity(processes.get(0), tasks1)
                , TasksByProcessResponseDto.fromEntity(processes.get(1), tasks2));
    }

    private List<Task> dummyTaskListForProcessId1() {
        return List.of(new Task(1L, "제목입니다", "내용입니다", "web", 1L, 0)
                , new Task(2L, "제목입니다", "내용입니다", "web", 1L, 0));
    }

    private List<Task> dummyTaskListForProcessId2() {
        return List.of(new Task(3L, "두번째 제목입니다.", "내용입니다", "web", 2L, 0)
                , new Task(4L, "두번째 제목입니다.", "내용입니다", "web", 2L, 0));
    }

    private List<Process> dummyProcessList() {
        return List.of(new Process(1L, "해야할 일"), new Process(2L, "하기싫은 일"));
    }

    private List<TaskResponseDto> dummyTaskResponseDto(List<Task> task) {
        return task.stream()
                .map(TaskResponseDto::fromEntity)
                .collect(Collectors.toUnmodifiableList());
    }
}