package codesquad.todolist.travelers.task.service;

import codesquad.todolist.travelers.actionType.ActionType;
import codesquad.todolist.travelers.annotation.ActionId;
import codesquad.todolist.travelers.global.CustomException;
import codesquad.todolist.travelers.global.ErrorCode;
import codesquad.todolist.travelers.process.repository.ProcessRepository;
import codesquad.todolist.travelers.task.domain.dto.request.TaskMoveRequestDto;
import codesquad.todolist.travelers.task.domain.dto.request.TaskRequestDto;
import codesquad.todolist.travelers.task.domain.dto.request.TaskUpdateRequestDto;
import codesquad.todolist.travelers.task.domain.dto.response.TaskPostResponseDto;
import codesquad.todolist.travelers.task.domain.dto.response.TaskResponseDto;
import codesquad.todolist.travelers.task.domain.dto.response.TasksByProcessResponseDto;
import codesquad.todolist.travelers.task.domain.entity.Task;
import codesquad.todolist.travelers.task.repository.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProcessRepository processRepository;

    public TaskService(final TaskRepository taskRepository, ProcessRepository processRepository) {
        this.taskRepository = taskRepository;
        this.processRepository = processRepository;
    }

    @ActionId(ActionType.CREATE_TASK)
    public TaskPostResponseDto createTask(final TaskRequestDto taskRequestDto) {
        List<Task> tasks = taskRepository.findAllBy(taskRequestDto.getProcessId());
        double position = Task.calculatePosition(tasks);

        Task task = TaskRequestDto.toEntity(taskRequestDto, position);
        Long taskId = taskRepository.save(task)
                .orElseThrow(() -> new CustomException(ErrorCode.FAIL_TASK_CREATE));

        return new TaskPostResponseDto(task, taskId);
    }

    @ActionId(ActionType.DELETE_TASK)
    public void deleteTask(final Long taskId) {
        taskRepository.deleteBy(taskId);
    }

    @ActionId(ActionType.UPDATE_TASK)
    public void updateTask(final Long taskId, final TaskUpdateRequestDto taskUpdateRequestDto) {
        taskRepository.updateBy(taskId, TaskUpdateRequestDto.toEntity(taskUpdateRequestDto));
    }

    @ActionId(ActionType.MOVE_TASK)
    public void updateTaskByProcess(final Long taskId, final TaskMoveRequestDto taskMoveRequestDto) {
        double updatePosition;

        if (taskMoveRequestDto.hasOnlyUp()) {
            updatePosition = calculatePositionForOnlyUp(taskMoveRequestDto);
        } else if (taskMoveRequestDto.hasOnlyDown()) {
            updatePosition = calculatePositionForOnlyDown(taskMoveRequestDto);
        } else if (taskMoveRequestDto.hasUpAndDown()) {
            updatePosition = calculatePositionForBoth(taskMoveRequestDto);
        } else { //task 이동시 upTask와 downTask가 없을때
            updatePosition = 1.0;
        }

        taskRepository.updateTaskBy(taskMoveRequestDto.getProcessId(), taskId, updatePosition);
    }

    private double calculatePositionForOnlyUp(TaskMoveRequestDto taskMoveRequestDto) {
        double upPosition = taskRepository.findPositionById(taskMoveRequestDto.getUpTaskId());
        return upPosition / 2.0;
    }

    private double calculatePositionForOnlyDown(TaskMoveRequestDto taskMoveRequestDto) {
        double downPosition = taskRepository.findPositionById(taskMoveRequestDto.getDownTaskId());
        return downPosition + 1.0;
    }

    private double calculatePositionForBoth(TaskMoveRequestDto taskMoveRequestDto) {
        double upPosition = taskRepository.findPositionById(taskMoveRequestDto.getUpTaskId());
        double downPosition = taskRepository.findPositionById(taskMoveRequestDto.getDownTaskId());
        return (upPosition + downPosition) / 2.0;
    }

    public List<TasksByProcessResponseDto> getAllTodoList() {
        return processRepository.findProcesses()
                .stream()
                .map(process -> TasksByProcessResponseDto.fromEntity(process, getTasksBy(process.getProcessId())))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<TaskResponseDto> getTasksBy(final Long processId) {
        return taskRepository.findAllBy(processId)
                .stream()
                .map(TaskResponseDto::fromEntity)
                .collect(Collectors.toUnmodifiableList());
    }

    public Task getTaskBy(final Long taskId) {
        return taskRepository.findByIgnoringDeleted(taskId);
    }
}
