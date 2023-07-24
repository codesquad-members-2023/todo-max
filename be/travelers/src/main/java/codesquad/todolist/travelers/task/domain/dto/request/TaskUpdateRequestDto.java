package codesquad.todolist.travelers.task.domain.dto.request;

import codesquad.todolist.travelers.task.domain.entity.Task;
import javax.validation.constraints.Size;

public class TaskUpdateRequestDto {
    @Size(min = 1, max = 50, message = "제목 글자 수는 1에서 50자 이하입니다.")
    private String title;
    @Size(min = 1, max = 500, message = "내용 글자 수는 1에서 500자 이하입니다.")
    private String contents;

    public TaskUpdateRequestDto() {
    }

    public TaskUpdateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public static Task toEntity(TaskUpdateRequestDto taskUpdateRequestDto) {
        return new Task(null, taskUpdateRequestDto.getTitle(), taskUpdateRequestDto.getContents(), null, null, 0);
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
