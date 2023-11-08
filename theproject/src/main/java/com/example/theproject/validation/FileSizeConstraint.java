package com.example.theproject.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileSizeValidator.class)
@Target( {ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSizeConstraint {
    int value() default 1000000;
    String message() default "Maximum file size is {value} bytes";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}