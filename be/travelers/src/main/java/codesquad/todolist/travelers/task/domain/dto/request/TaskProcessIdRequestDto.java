package codesquad.todolist.travelers.task.domain.dto.request;

public class TaskProcessIdRequestDto {
    private Long processId;

    public TaskProcessIdRequestDto() {
    }

    public TaskProcessIdRequestDto(Long processId) {
        this.processId = processId;
    }

    public Long getProcessId() {
        return processId;
    }
}
