package com.jandcode.mycv.exception;

import org.springframework.http.HttpStatus;

public class GeneralErrorException extends RuntimeException {
    private final HttpStatus status;

    public GeneralErrorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
