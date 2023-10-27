package com.graphql.assignment.exception;

import com.graphql.assignment.model.exception.ValidationError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistsException extends RuntimeException {
    private ValidationError validationError;

    public UserAlreadyExistsException(String message, String code) {
        super(message);
        validationError = new ValidationError(message, code);
    }
}
