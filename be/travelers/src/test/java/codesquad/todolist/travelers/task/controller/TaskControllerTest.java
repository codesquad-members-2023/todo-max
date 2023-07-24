package codesquad.todolist.travelers.task.controller;

import static codesquad.todolist.travelers.global.SuccessCode.TASK_SUCCESS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codesquad.todolist.travelers.annotation.ControllerTest;
import codesquad.todolist.travelers.process.domain.entity.Process;
import codesquad.todolist.travelers.task.domain.dto.request.TaskMoveRequestDto;
import codesquad.todolist.travelers.task.domain.dto.request.TaskRequestDto;
import codesquad.todolist.travelers.task.domain.dto.request.TaskUpdateRequestDto;
import codesquad.todolist.travelers.task.domain.dto.response.TaskPostResponseDto;
import codesquad.todolist.travelers.task.domain.dto.response.TaskResponseDto;
import codesquad.todolist.travelers.task.domain.dto.response.TasksByProcessResponseDto;
import codesquad.todolist.travelers.task.domain.entity.Task;
import codesquad.todolist.travelers.task.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ControllerTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("모든 컬럼과 모든 카드를 반환한다.")
    void getSuccessTest() throws Exception {
        //given
        given(taskService.getAllTodoList()).willReturn(dummyProcessResponseDto());

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(TASK_SUCCESS.getCustomStatus()))
                .andExpect(jsonPath("$.message[0].processId").value(1))
                .andExpect(jsonPath("$.message[0].name").value("해야할 일"))
                .andExpect(jsonPath("$.message[0].tasks[0].taskId").value(1));
    }

    private List<TasksByProcessResponseDto> dummyProcessResponseDto() {
        return List.of(TasksByProcessResponseDto.fromEntity(dummyProcess(), dummyTaskResponseDto()));
    }

    private Process dummyProcess() {
        return new Process(1L, "해야할 일");
    }

    private List<TaskResponseDto> dummyTaskResponseDto() {
        return List.of(TaskResponseDto.fromEntity(dummyTask()));
    }

    private Task dummyTask() {
        return new Task(1L, "제목입니다", "내용입니다", "web", 1L, 0);
    }

    private TaskRequestDto dummyTaskRequestDto() {
        return new TaskRequestDto("제목입니다", "내용입니다", "web", 1L);
    }

    private TaskPostResponseDto dummyTaskPostResponseDto() {
        return new TaskPostResponseDto(dummyTask(), dummyTask().getTaskId());
    }


    // 지안
    // 1. 등록
    @Test
    @DisplayName("카드를 생성하면 해당 카드의 정보가 반환된다.")
    void createTask() throws Exception {

        given(taskService.createTask(any()))
                .willReturn(dummyTaskPostResponseDto());

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/task")
                .content(objectMapper.writeValueAsString(dummyTaskRequestDto()))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(TASK_SUCCESS.getCustomStatus()))
                .andExpect(jsonPath("$.message.taskId").value(1))
                .andExpect(jsonPath("$.message.processId").value(1))
                .andExpect(jsonPath("$.message.title").value("제목입니다"))
                .andExpect(jsonPath("$.message.contents").value("내용입니다"))
                .andExpect(jsonPath("$.message.platform").value("web"));
    }

    // 2. 삭제

    @Test
    @DisplayName("카드를 삭제할 수 있다.")
    void deleteTask() throws Exception {
        // given
        doNothing().when(taskService).deleteTask(1L);

        // when
        ResultActions resultActions = mockMvc.perform(
                delete("/api/task/{taskId}", 1L)
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(TASK_SUCCESS.getCustomStatus()))
                .andExpect(jsonPath("$.message").value(TASK_SUCCESS.getMessage()));

    }

    /////////////////////////////////////////////////////////////////////////////

    // 설리
    // 1. 수정
    @Test
    @DisplayName("PATCH 요청으로 고유 ID(taskId)에 따른 카드를 수정한다.")
    void updateSuccessTest() throws Exception {
        //given
        doNothing().when(taskService).updateTask(1L, dummyTaskUpdateRequestDto());

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/task/{taskId}", 1L)
                // @RequestBody를 위해 아래의 코드를 추가해 줘야 됨 (Body를 넣어준다는 뜻)
                .content(objectMapper.writeValueAsString(dummyTaskUpdateRequestDto()))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(TASK_SUCCESS.getCustomStatus()))
                .andExpect(jsonPath("$.message").value(TASK_SUCCESS.getMessage()));
    }

    private TaskUpdateRequestDto dummyTaskUpdateRequestDto() {
        return new TaskUpdateRequestDto("수정 타이틀", "수정 내용");
    }

    // 2. 이동
    @Test
    @DisplayName("PATCH 요청으로 원하는 칼럼(process)으로 카드를 이동시킨다.")
    void moveSuccessTest() throws Exception {
        // given
        doNothing().when(taskService)
                .updateTaskByProcess(1L, dummyTaskProcessIdRequestDto());

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/task/process/{taskId}", 1L)
                // @RequestBody를 위해 아래의 코드를 추가해 줘야 됨 (Body를 넣어준다는 뜻)
                .content(objectMapper.writeValueAsString(dummyTaskProcessIdRequestDto()))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(TASK_SUCCESS.getCustomStatus()))
                .andExpect(jsonPath("$.message").value(TASK_SUCCESS.getMessage()));
    }

    private TaskMoveRequestDto dummyTaskProcessIdRequestDto() {
        return new TaskMoveRequestDto(1L);
    }
}