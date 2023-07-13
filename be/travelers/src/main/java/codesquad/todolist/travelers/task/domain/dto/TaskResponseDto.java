package codesquad.todolist.travelers.task.domain.dto;

import codesquad.todolist.travelers.task.domain.entity.Task;

public class TaskResponseDto {
    private final Long taskId;
    private final String title;
    private final String contents;
    private final String platform;
    private final String createdTime;
    private final Long processId;

    public TaskResponseDto(final Task task) {
        this.taskId = task.getTaskId();
        this.title = task.getTitle();
        this.contents = task.getContents();
        this.platform = task.getPlatform();
        this.createdTime = task.getCreatedTime();
        this.processId = task.getProcessId();
    }

    public Task toEntity() {
        return new Task(taskId, title, contents, platform, createdTime, processId);
    }
}
