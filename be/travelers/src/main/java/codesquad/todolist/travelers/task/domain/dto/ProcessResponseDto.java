package codesquad.todolist.travelers.task.domain.dto;

import codesquad.todolist.travelers.task.domain.entity.Process;
import java.util.List;

public class ProcessResponseDto {
    private final Long processId;
    private final String name;
    private final List<TaskResponseDto> tasks;

    private ProcessResponseDto(final Long processId, final String name, final List<TaskResponseDto> tasks) {
        this.processId = processId;
        this.name = name;
        this.tasks = tasks;
    }

    public static ProcessResponseDto from(final Process process, final List<TaskResponseDto> tasks) {
        return new ProcessResponseDto(process.getProcessId(), process.getName(), tasks);
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
