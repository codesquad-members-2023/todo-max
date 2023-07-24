package codesquad.todolist.travelers.global;

public class CustomException extends RuntimeException {

    private StatusCode statusCode;

    public CustomException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
