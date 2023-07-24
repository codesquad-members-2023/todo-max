package codesquad.todolist.travelers.global;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements StatusCode {
    // TODO: 에러에 맞게 수정하기
    FAIL_TASK_CREATE(HttpStatus.SERVICE_UNAVAILABLE, "FT01", "Task 생성에 실패 했습니다."),
    FAIL_HISTORY_CREATE(HttpStatus.SERVICE_UNAVAILABLE, "FH01", "History 생성에 실패 했습니다."),
    FAIL_PROCESS_CREATE(HttpStatus.SERVICE_UNAVAILABLE, "PH01", "Process 생성에 실패 했습니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "FV01"), // 입력 값 범위 초과
    NOT_EXIST_PROCESS(HttpStatus.NOT_FOUND, "PH02", "해당 ID를 가지는 Process가 존재하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private String message;
    private final String customStatus;

    ErrorCode(HttpStatus httpStatus, String customStatus, String message) {
        this.httpStatus = httpStatus;
        this.customStatus = customStatus;
        this.message = message;
    }

    ErrorCode(HttpStatus httpStatus, String customStatus) {
        this.httpStatus = httpStatus;
        this.customStatus = customStatus;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getCustomStatus() {
        return this.customStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
