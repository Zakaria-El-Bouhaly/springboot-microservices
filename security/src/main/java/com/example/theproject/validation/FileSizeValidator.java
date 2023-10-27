package com.example.theproject.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements
        ConstraintValidator<FileSizeConstraint, MultipartFile> {

    private int maxSize;
    // constructor to take the value from the annotation and initialize the validator with it



    @Override
    public void initialize(FileSizeConstraint contactNumber) {
        this.maxSize = contactNumber.value();
    }

    @Override
    public boolean isValid(MultipartFile file
            , ConstraintValidatorContext cxt) {
        return file.getSize() < maxSize;

    }
}