package sit.int221.announcement.exceptions.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import sit.int221.announcement.exceptions.impl.CloseAfterPublishImpl;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CloseAfterPublishImpl.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CloseAfterPublish {
    String message() default "must be later than publish date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String field() default "closeDate";
}