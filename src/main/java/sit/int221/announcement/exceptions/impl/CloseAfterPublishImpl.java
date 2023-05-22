package sit.int221.announcement.exceptions.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import sit.int221.announcement.dtos.AnnouncementRequestDTO;
import sit.int221.announcement.exceptions.validator.CloseAfterPublish;

import java.time.ZonedDateTime;

public class CloseAfterPublishImpl implements ConstraintValidator<CloseAfterPublish, AnnouncementRequestDTO> {

    @Override
    public void initialize(CloseAfterPublish constraintAnnotation) {}

    @Override
    public boolean isValid(AnnouncementRequestDTO request, ConstraintValidatorContext context) {
        ZonedDateTime publish = request.getPublishDate();
        ZonedDateTime close = request.getCloseDate();
        if (close == null || publish == null) return true;
        return close.isAfter(publish);
    }
}