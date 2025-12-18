package com.jandcode.mycv.exception;

import com.jandcode.mycv.entity.response.GeneralErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(GeneralErrorException.class)
    public ResponseEntity<GeneralErrorResponse> handleGeneralError(GeneralErrorException ex) {

        GeneralErrorResponse error =
                new GeneralErrorResponse(ex.getMessage(), ex.getStatus().value());

        return new ResponseEntity<>(error, ex.getStatus());
    }
}
