package codesquad.todolist.travelers.process.domain.dto;

import codesquad.todolist.travelers.process.domain.entity.Process;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Size;

public class ProcessRequestDto {

    @ApiModelProperty(example = "해야하지만 하고싶지 않은 일", required = true)
    @Size(min = 1, max = 50, message = "컬럼 글자 수는 1이상 50이하여야 합니다.")
    private String processName;

    public ProcessRequestDto() {
    }

    public static Process toEntity(ProcessRequestDto processRequestDto) {
        return new Process(processRequestDto.processName);
    }

    public String getProcessName() {
        return processName;
    }
}
