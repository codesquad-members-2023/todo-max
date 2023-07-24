package codesquad.todolist.travelers.task.domain.entity;

import java.util.List;

public class Task {
    private final Long taskId;
    private final String title;
    private final String contents;
    private final String platform;
    private final Long processId;
    private final double position;

    public Task(Long taskId, String title, String contents, String platform, Long processId,
                double position) {
        this.taskId = taskId;
        this.title = title;
        this.contents = contents;
        this.platform = platform;
        this.processId = processId;
        this.position = position;
    }

    public double getPosition() {
        return position;
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

    public Long getProcessId() {
        return processId;
    }

    public static double calculatePosition(List<Task> tasks) {
        // 컬럼에 데이터가 없을 때
        if (tasks.isEmpty()) {
            return 1.0;
        }
        // 컬럼에 데이터가 있을 때
        return tasks.stream()
                .mapToDouble(Task::getPosition)
                .max()
                .getAsDouble() + 1;
    }
}
