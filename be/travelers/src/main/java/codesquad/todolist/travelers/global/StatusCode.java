package codesquad.todolist.travelers.global;

import org.springframework.http.HttpStatus;

public interface StatusCode {

    String name();

    HttpStatus getHttpStatus();

    String getMessage();

    String getCustomStatus();
}
