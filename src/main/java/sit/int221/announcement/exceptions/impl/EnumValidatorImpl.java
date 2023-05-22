package sit.int221.announcement.exceptions.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sit.int221.announcement.exceptions.validator.EnumValidator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    Set<String> enums;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        enums = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return enums.contains(value);
    }
}
