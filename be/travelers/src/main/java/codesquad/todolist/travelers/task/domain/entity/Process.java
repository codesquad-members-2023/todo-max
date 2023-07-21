package codesquad.todolist.travelers.task.domain.entity;

public class Process {
    private Long processId;
    private String name;

    public Process(Long processId, String name) {
        this.processId = processId;
        this.name = name;
    }

    public Long getProcessId() {
        return processId;
    }

    public String getName() {
        return name;
    }
}
