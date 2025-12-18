package com.jandcode.mycv.utils;

import com.jandcode.mycv.exception.GeneralErrorException;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class FormWebUtils {
    public void validatedEmptyText(String variableModel, String msgError, HttpStatus httpStatus) {
        if (variableModel == null || variableModel.trim().isEmpty()) {
            throw new GeneralErrorException(msgError, httpStatus);
        }
    }

    public void validatedMaxLength(String value, String fieldName, int maxLength, HttpStatus status) {
        if (value != null && value.length() > maxLength) {
            String msg = MessageFormat.format(Constants.MSG_LENGTH_FIELDS, fieldName, maxLength);
            throw new GeneralErrorException(msg, status);
        }
    }

    public void validatedAllowedCharacters(String value, String fieldName, String regex, HttpStatus status) {
        if (value != null && !value.matches(regex)) {
            String msg = MessageFormat.format(Constants.MSG_REGEX_INVALID_FIELDS, fieldName);
            throw new GeneralErrorException(msg, status);
        }
    }
}
