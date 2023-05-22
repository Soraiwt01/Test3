package sit.int221.announcement.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private DateTimeException timestamp;
    private final int status;
    private final String instance;
    private String message;
    private String stackTrace;
    private List<ValidationError> detail;

    public ErrorResponse(int status,String instance) {
        this.status = status;
        this.instance = instance;
    }
    public ErrorResponse(int status,String message,String instance) {
        this.status = status;
        this.message = message;
        this.instance = instance;
    }

    @Getter @Setter
    @RequiredArgsConstructor
    private static class ValidationError {
        private final String field;
        private final String errorMessage;
    }

    public void addValidationError(String field,String message) {
        if (Objects.isNull(detail)) detail = new ArrayList<>();
        detail.add(new ValidationError(field,message));
    }


}
