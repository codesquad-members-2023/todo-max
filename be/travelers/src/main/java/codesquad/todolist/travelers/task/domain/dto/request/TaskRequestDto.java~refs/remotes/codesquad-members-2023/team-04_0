package codesquad.todolist.travelers.task.domain.dto.request;

import codesquad.todolist.travelers.task.domain.entity.Task;
import javax.validation.constraints.Size;

public class TaskRequestDto {
    @Size(min = 1, max = 50, message = "제목 글자 수는 1에서 50자 이하입니다.")
    private String title;
    @Size(min = 1, max = 500, message = "내용 글자 수는 1에서 500자 이하입니다.")
    private String contents;
    private String platform;
    private Long processId;

    public TaskRequestDto() {
    }

    public TaskRequestDto(final String title, final String contents, final String platform, final Long processId) {
        this.title = title;
        this.contents = contents;
        this.platform = platform;
        this.processId = processId;
    }

    public static Task toEntity(TaskRequestDto taskRequestDto, double position) {
        return new Task(null, taskRequestDto.getTitle(), taskRequestDto.getContents(), taskRequestDto.getPlatform(),
                taskRequestDto.getProcessId(), position);
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Long getProcessId() {
        return processId;
    }

    public String getPlatform() {
        return platform;
    }

}
