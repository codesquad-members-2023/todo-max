package codesquad.todolist.travelers.task.domain.dto;

import java.util.Map;

public class ResponseDto {
    private boolean success;
    private Map<String, Object> data;
    private Map<String, Object> errorCode;

    public ResponseDto() {
    }

    public ResponseDto(final boolean success, final Map<String, Object> data, final Map<String, Object> errorCode) {
        this.success = success;
        this.data = data;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Map<String, Object> getErrorCode() {
        return errorCode;
    }
}
