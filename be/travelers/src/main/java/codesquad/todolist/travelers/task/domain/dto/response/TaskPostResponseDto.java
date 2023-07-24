package codesquad.todolist.travelers.task.domain.dto.response;

import codesquad.todolist.travelers.task.domain.entity.Task;

public class TaskPostResponseDto {
    private final Long taskId;
    private final Long processId;
    private final String title;
    private final String contents;
    private final String platform;

    public TaskPostResponseDto(final Task task, final Long taskId) {
        this.taskId = taskId;
        this.processId = task.getProcessId();
        this.title = task.getTitle();
        this.contents = task.getContents();
        this.platform = task.getPlatform();
    }

    public Long getProcessId() {
        return processId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getPlatform() {
        return platform;
    }
}
