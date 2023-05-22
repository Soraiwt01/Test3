package sit.int221.announcement.exceptions.list;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String field,String message) {
        super(field,new Throwable(message));
    }
}
