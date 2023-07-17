package codesquad.todolist.travelers.global;

public class ApiResponse<T> {

    private String status;
    private T message;

    public ApiResponse(String status, T message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public T getMessage() {
        return message;
    }

    public static <T> ApiResponse<T> success(String status, T message) {
        return new ApiResponse<>(status, message);
    }
    public static <T> ApiResponse<T> fail(String status, T message) {
        return new ApiResponse<>(status, message);
    }
}
