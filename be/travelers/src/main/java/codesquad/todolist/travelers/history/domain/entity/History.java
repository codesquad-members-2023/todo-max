package codesquad.todolist.travelers.history.domain.entity;

import java.time.LocalDateTime;

public class History {
    private final Long historyId;
    private final String title;
    private final String from;
    private final String to;
    private final Long actionId;
    private final LocalDateTime createdTime;
    private final Long userId;

    private History(Builder builder) {
        this.historyId = builder.historyId;
        this.title = builder.title;
        this.from = builder.from;
        this.to = builder.to;
        this.actionId = builder.actionId;
        this.createdTime = builder.createdTime;
        this.userId = builder.userId;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public String getTitle() {
        return title;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Long getActionId() {
        return actionId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Long getUserId() {
        return userId;
    }

    public static class Builder {

        private Long historyId;
        private String title;
        private String from;
        private String to;
        private Long actionId;
        private LocalDateTime createdTime;
        private Long userId;

        public Builder historyId(Long historyId) {
            this.historyId = historyId;
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

        public Builder actionId(Long actionId) {
            this.actionId = actionId;
            return this;
        }

        public Builder createdTime(LocalDateTime createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public History build() {
            return new History(this);
        }
    }
}
