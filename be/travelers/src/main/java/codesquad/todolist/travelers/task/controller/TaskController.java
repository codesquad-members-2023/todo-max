package codesquad.todolist.travelers.task.controller;

import static codesquad.todolist.travelers.global.SuccessCode.TASK_SUCCESS;

import codesquad.todolist.travelers.global.CommonApiResponse;
import codesquad.todolist.travelers.task.domain.dto.request.TaskMoveRequestDto;
import codesquad.todolist.travelers.task.domain.dto.request.TaskRequestDto;
import codesquad.todolist.travelers.task.domain.dto.request.TaskUpdateRequestDto;
import codesquad.todolist.travelers.task.domain.dto.response.TaskPostResponseDto;
import codesquad.todolist.travelers.task.domain.dto.response.TasksByProcessResponseDto;
import codesquad.todolist.travelers.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "todolist 가져오기", description = "서버로부터 todolist에 필요한 모든 데이터를 가져온다.")
    @GetMapping("/")
    public ResponseEntity<CommonApiResponse<?>> get() {
        List<TasksByProcessResponseDto> processes = taskService.getAllTodoList();

        return ResponseEntity.status(TASK_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(TASK_SUCCESS.getCustomStatus(),
                        processes));
    }

    @Operation(summary = "카드 등록", description = "POST 요청으로 각 칼럼(process)에 대한 task를 등록한다.")
    @PostMapping("/task")
    public ResponseEntity<CommonApiResponse<?>> add(@RequestBody @Valid final TaskRequestDto taskRequestDto) {
        TaskPostResponseDto task = taskService.createTask(taskRequestDto);

        return ResponseEntity.status(TASK_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(TASK_SUCCESS.getCustomStatus(), task));
    }

    @Operation(summary = "카드 삭제", description = "DELETE 요청으로 고유 ID에 따른 카드를 삭제한다.")
    @Parameter(name = "taskId", description = "카드의 고유 ID")
    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<CommonApiResponse<?>> delete(@PathVariable final Long taskId) {
        taskService.deleteTask(taskId);

        return ResponseEntity.status(TASK_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(TASK_SUCCESS.getCustomStatus(), TASK_SUCCESS.getMessage()));
    }

    @Operation(summary = "카드 수정", description = "PATCH 요청으로 고유 ID에 따른 카드를 수정한다.")
    @Parameter(name = "taskId", description = "카드의 고유 ID")
    @PatchMapping("/task/{taskId}")
    public ResponseEntity<CommonApiResponse<?>> update(@PathVariable final Long taskId,
                                                       @RequestBody @Valid final TaskUpdateRequestDto taskUpdateRequestDto) {
        taskService.updateTask(taskId, taskUpdateRequestDto);

        return ResponseEntity.status(TASK_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(TASK_SUCCESS.getCustomStatus(), TASK_SUCCESS.getMessage()));
    }

    @Operation(summary = "카드 이동", description = "PATCH 요청으로 원하는 칼럼(process)으로 카드를 이동시킨다. body로는 processId를 받는다.")
    @Parameter(name = "taskId", description = "카드의 고유 ID")
    @PatchMapping("/task/process/{taskId}")
    public ResponseEntity<CommonApiResponse<?>> move(@PathVariable final Long taskId,
                                                     @RequestBody final TaskMoveRequestDto taskMoveRequestDto) {
        taskService.updateTaskByProcess(taskId, taskMoveRequestDto);

        return ResponseEntity.status(TASK_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(TASK_SUCCESS.getCustomStatus(), TASK_SUCCESS.getMessage()));
    }

    @Operation(summary = "카드 삭제", description = "DELETE 요청으로 고유 ID에 따른 카드를 삭제한다.")
    @Parameter(name = "taskId", description = "카드의 고유 ID")
    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonApiResponse.success("200", "카드 삭제 성공"));
    }

    @Operation(summary = "카드 수정", description = "PATCH 요청으로 고유 ID에 따른 카드를 수정한다.")
    @Parameter(name = "taskId", description = "카드의 고유 ID")
    @PatchMapping("/task/{taskId}")
    public ResponseEntity<CommonApiResponse<?>> update(@PathVariable final Long taskId,
                                                       @RequestBody @Valid final TaskUpdateRequestDto taskUpdateRequestDto) {
        taskService.updateTask(taskId, taskUpdateRequestDto);

        return ResponseEntity.status(TASK_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(TASK_SUCCESS.getCustomStatus(), TASK_SUCCESS.getMessage()));
    }

    @Operation(summary = "카드 이동", description = "PATCH 요청으로 원하는 칼럼(process)으로 카드를 이동시킨다. body로는 processId를 받는다.")
    @Parameter(name = "taskId", description = "카드의 고유 ID")
    @PatchMapping("/task/process/{taskId}")
    public ResponseEntity<CommonApiResponse<?>> move(@PathVariable final Long taskId,
                                                     @RequestBody final TaskMoveRequestDto taskMoveRequestDto) {
        taskService.updateTaskByProcess(taskId, taskMoveRequestDto);

        return ResponseEntity.status(TASK_SUCCESS.getHttpStatus())
                .body(CommonApiResponse.success(TASK_SUCCESS.getCustomStatus(), TASK_SUCCESS.getMessage()));
    }

    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable final Long taskId) {
        taskService.deleteTask(taskId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("200", "카드 삭제 성공!"));
    }

    @PatchMapping("/task/{taskId}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable final Long taskId,
                                                 @RequestBody final TaskUpdateRequestDto taskUpdateRequestDto) {
        taskService.updateTask(taskId, taskUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("200", "카드 수정 성공!"));
    }

    @PatchMapping("/task/process/{taskId}")
    public ResponseEntity<ApiResponse<?>> move(@PathVariable final Long taskId,
                                               @RequestBody final TaskProcessIdRequestDto taskProcessIdRequestDto) {
        taskService.updateTaskByProcess(taskProcessIdRequestDto, taskId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("200", "카드 이동 성공!"));
    }
}
