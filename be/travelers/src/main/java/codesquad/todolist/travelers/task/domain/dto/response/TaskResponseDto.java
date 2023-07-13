package codesquad.todolist.travelers.task.domain.dto.response;

import codesquad.todolist.travelers.task.domain.entity.Task;
import java.time.LocalDateTime;

public class TaskResponseDto {
    private final Long taskId;
    private final String title;
    private final String contents;
    private final String platform;

    private TaskResponseDto(Long taskId, String title, String contents, String platform) {
        this.taskId = taskId;
        this.title = title;
        this.contents = contents;
        this.platform = platform;
    }

    public static TaskResponseDto fromEntity(final Task task) {
        return new TaskResponseDto(task.getTaskId(), task.getTitle(), task.getContents(), task.getPlatform());
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
