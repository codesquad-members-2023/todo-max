package codesquad.todolist.travelers.history.domain;

import java.time.LocalDateTime;

public class ActionHistory {
    private final String title;
    private final String from;
    private final String to;
    private final String action;
    private final LocalDateTime createdTime;
    private final String userName;
    private final String imageURL;

    public ActionHistory(String title, String from, String to, String action, LocalDateTime createdTime,
                         String userName,
                         String imageURL) {
        this.title = title;
        this.from = from;
        this.to = to;
        this.action = action;
        this.createdTime = createdTime;
        this.userName = userName;
        this.imageURL = imageURL;
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

    public String getAction() {
        return action;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getImageURL() {
        return imageURL;
    }
}
