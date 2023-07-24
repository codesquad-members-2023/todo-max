package codesquad.todolist.travelers.global;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonApiResponse<?>> handleCustomException(CustomException e) {
        StatusCode statusCode = e.getStatusCode();

        return ResponseEntity.status(statusCode.getHttpStatus())
                .body(CommonApiResponse.fail(statusCode.getCustomStatus(), statusCode.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonApiResponse<?>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        StatusCode statusCode = ErrorCode.VALIDATION_FAILED;

        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();

        StringBuilder errorMessage = new StringBuilder();
        for (ObjectError error : objectErrors) {
            errorMessage.append(error.getDefaultMessage()).append("\n");
        }

        return ResponseEntity.status(statusCode.getHttpStatus())
                .body(CommonApiResponse.fail(statusCode.getCustomStatus(), errorMessage.toString()));
    }

}
