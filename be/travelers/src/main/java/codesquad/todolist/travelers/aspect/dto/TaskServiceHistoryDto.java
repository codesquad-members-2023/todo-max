package codesquad.todolist.travelers.aspect.dto;

import codesquad.todolist.travelers.actionType.ActionType;
import codesquad.todolist.travelers.history.domain.entity.History;
import codesquad.todolist.travelers.task.domain.entity.Task;

public class TaskServiceHistoryDto {
    private ActionType actionType;
    private String title;
    private String from;
    private String to;

    private TaskServiceHistoryDto(Builder builder) {
        this.actionType = builder.actionType;
        this.title = builder.title;
        this.from = builder.from;
        this.to = builder.to;
    }

    public History toEntity() {
        return new History.Builder()
                .title(title)
                .from(from)
                .to(to)
                .actionId(actionType.getActionId())
                .userId(1L)
                .build();
    }

    public static TaskServiceHistoryDto of(Task task, String fromName, String toName, ActionType actionType) {
        return TaskServiceHistoryDto.builder()
                .title(task.getTitle())
                .from(fromName)
                .to(toName)
                .actionType(actionType)
                .build();
    }

    public static TaskServiceHistoryDto of(Task task, String fromName, ActionType actionType) {
        return TaskServiceHistoryDto.builder()
                .title(task.getTitle())
                .from(fromName)
                .actionType(actionType)
                .build();
    }

    public static TaskServiceHistoryDto of(Task task, ActionType actionType) {
        return TaskServiceHistoryDto.builder()
                .title(task.getTitle())
                .actionType(actionType)
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ActionType actionType;
        private String title;
        private String from;
        private String to;

        public Builder actionType(ActionType actionType) {
            this.actionType = actionType;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public TaskServiceHistoryDto build() {
            return new TaskServiceHistoryDto(this);
        }
    }
}
