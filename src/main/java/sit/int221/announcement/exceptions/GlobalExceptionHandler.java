package sit.int221.announcement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import sit.int221.announcement.exceptions.list.ItemNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,WebRequest request) {
        ErrorResponse response = new ErrorResponse(BAD_REQUEST.value(),e.getClass().getSimpleName(),getUri(request));
        e.getBindingResult().getAllErrors().forEach((field) -> {
            response.addValidationError(getFieldName(field),field.getDefaultMessage());
        });
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler( ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundException e,WebRequest request) {
        ErrorResponse response = new ErrorResponse(BAD_REQUEST.value(),e.getClass().getSimpleName(),getUri(request));
        response.addValidationError(e.getMessage(), e.getCause().getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    private String getFieldName(ObjectError field) {
        String name;
        if (field instanceof FieldError) name = ((FieldError) field).getField();
        else {
            Object[] arguments = field.getArguments();
            name = arguments == null ? field.getCode() : arguments[arguments.length-1].toString();
        }
        return name;
    }

    private String getUri(WebRequest request) {
        return request.getDescription(false).substring(4);
    }
}
