package com.signatur3.data.errors;

import lombok.experimental.StandardException;

@StandardException
public class ApplicationError extends RuntimeException {

    @StandardException
    public static class NotFound extends ApplicationError {
    }

}
