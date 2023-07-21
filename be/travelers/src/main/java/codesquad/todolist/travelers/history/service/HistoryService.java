package codesquad.todolist.travelers.history.service;


import codesquad.todolist.travelers.actionType.ActionType;
import codesquad.todolist.travelers.aspect.dto.TaskServiceHistoryDto;
import codesquad.todolist.travelers.global.CustomException;
import codesquad.todolist.travelers.global.ErrorCode;
import codesquad.todolist.travelers.history.domain.dto.response.ActionHistoryResponseDto;
import codesquad.todolist.travelers.history.domain.entity.History;
import codesquad.todolist.travelers.history.repository.HistoryRepository;
import codesquad.todolist.travelers.process.repository.ProcessRepository;
import codesquad.todolist.travelers.task.domain.entity.Task;
import codesquad.todolist.travelers.task.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final TaskRepository taskRepository;
    private final ProcessRepository processRepository;

    public HistoryService(HistoryRepository historyRepository, TaskRepository taskRepository,
                          ProcessRepository processRepository) {
        this.historyRepository = historyRepository;
        this.taskRepository = taskRepository;
        this.processRepository = processRepository;

    }

    public void deleteAllHistory() {
        historyRepository.deleteAll();
    }

    public List<ActionHistoryResponseDto> getAllHistory() {
        return historyRepository.findAll().stream()
                .map(ActionHistoryResponseDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public void saveActionCreateHistory(Long taskId, ActionType actionType) {
        Task task = taskRepository.findByIgnoringDeleted(taskId);
        String fromName = processRepository.findProcessNameBy(task.getProcessId());
        TaskServiceHistoryDto taskServiceHistoryDto = TaskServiceHistoryDto.of(task, fromName, actionType);

        saveHistory(taskServiceHistoryDto);
    }

    public void saveActionMoveHistory(Long taskId, String fromName, ActionType actionType) {
        Task task = taskRepository.findByIgnoringDeleted(taskId);
        String toName = processRepository.findProcessNameBy(task.getProcessId());
        TaskServiceHistoryDto taskServiceHistoryDto = TaskServiceHistoryDto.of(task, fromName, toName, actionType);

        saveHistory(taskServiceHistoryDto);
    }

    public void saveActionDeleteAndUpdateHistory(Long taskId, ActionType actionType) {
        Task task = taskRepository.findByIgnoringDeleted(taskId);

        TaskServiceHistoryDto taskServiceHistoryDto = null;
        switch (actionType) {
            case DELETE_TASK:
                String fromName = processRepository.findProcessNameBy(task.getProcessId());
                taskServiceHistoryDto = TaskServiceHistoryDto.of(task, fromName, actionType);
                break;
            case UPDATE_TASK:
                taskServiceHistoryDto = TaskServiceHistoryDto.of(task, actionType);
                break;
        }

        if (taskServiceHistoryDto != null) {
            saveHistory(taskServiceHistoryDto);
        }
    }

    private void saveHistory(TaskServiceHistoryDto taskServiceHistoryDto) {
        History history = taskServiceHistoryDto.toEntity();
        historyRepository.save(history).orElseThrow(() -> new CustomException(ErrorCode.FAIL_HISTORY_CREATE));
    }
}
