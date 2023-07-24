package codesquad.todolist.travelers.task.domain.dto.request;

public class TaskMoveRequestDto {
    private Long processId;
    private long upTaskId;
    private long downTaskId;

    public TaskMoveRequestDto() {
    }

    public TaskMoveRequestDto(Long processId, long upTaskId, long downTaskId) {
        this.processId = processId;
        this.upTaskId = upTaskId;
        this.downTaskId = downTaskId;
    }

    public TaskMoveRequestDto(Long processId) {
        this.processId = processId;
    }

    public Long getProcessId() {
        return processId;
    }

    public long getUpTaskId() {
        return upTaskId;
    }

    public long getDownTaskId() {
        return downTaskId;
    }

    public boolean hasOnlyUp() {
        return this.getUpTaskId() != 0 && this.getDownTaskId() == 0;
    }

    public boolean hasOnlyDown() {
        return this.getUpTaskId() == 0 && this.getDownTaskId() != 0;
    }

    public boolean hasUpAndDown() {
        return this.getUpTaskId() != 0 && this.getDownTaskId() != 0;
    }
}
