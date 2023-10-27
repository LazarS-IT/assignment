package com.graphql.assignment.exception;

import com.graphql.assignment.model.exception.ValidationError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {
    private ValidationError validationError;

    public EntityNotFoundException(String message, String code) {
        super(message);
        validationError = new ValidationError(message, code);
    }
}
