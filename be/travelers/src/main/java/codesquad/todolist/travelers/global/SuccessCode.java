package codesquad.todolist.travelers.global;

import org.springframework.http.HttpStatus;

public enum SuccessCode implements StatusCode {
    // 카드 요청
    TASK_SUCCESS(HttpStatus.OK, "T01", "카드 요청 성공!"),
    // 히스토리 요청
    HISTORY_SUCCESS(HttpStatus.OK, "H01", "히스토리 요청 성공!"),
    // 컬럼 요청
    PROCESS_SUCCESS(HttpStatus.OK, "P01", "컬럼 요청 성공!"),
    ;

    private final HttpStatus httpStatus;
    private final String customStatus;
    private final String message;

    SuccessCode(HttpStatus httpStatus, String customStatus, String message) {
        this.httpStatus = httpStatus;
        this.customStatus = customStatus;
        this.message = message;
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
