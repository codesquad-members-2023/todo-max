package codesquad.todolist.travelers.task.domain.dto.response;

import codesquad.todolist.travelers.process.domain.entity.Process;
import java.util.List;

public class TasksByProcessResponseDto {
    private final Long processId;
    private final String name;
    private final List<TaskResponseDto> tasks;

    private TasksByProcessResponseDto(final Long processId, final String name, final List<TaskResponseDto> tasks) {
        this.processId = processId;
        this.name = name;
        this.tasks = tasks;
    }

    public static TasksByProcessResponseDto fromEntity(final Process process, final List<TaskResponseDto> tasks) {
        return new TasksByProcessResponseDto(process.getProcessId(), process.getName(), tasks);
    }

    public Long getProcessId() {
        return processId;
    }

    public String getName() {
        return name;
    }

    public List<TaskResponseDto> getTasks() {
        return tasks;
    }
}
