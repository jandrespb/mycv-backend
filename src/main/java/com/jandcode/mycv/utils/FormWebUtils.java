package com.jandcode.mycv.utils;

import com.jandcode.mycv.exception.GeneralErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class FormWebUtils {

    private static final Logger log = LoggerFactory.getLogger(FormWebUtils.class);

    public void validatedEmptyText(String variableModel, String msgError, HttpStatus httpStatus) {

        if (variableModel == null || variableModel.trim().isEmpty()) {
            log.warn(msgError);
            throw new GeneralErrorException(msgError, httpStatus);
        }
    }

    public void validatedMaxLength(String value, String fieldName, int maxLength, HttpStatus status) {
        if (value != null && value.length() > maxLength) {
            String msg = MessageFormat.format(Constants.MSG_LENGTH_FIELDS, fieldName, maxLength);
            log.warn(msg);
            throw new GeneralErrorException(Constants.MSG_VALIDATE_FIELD, status);
        }
    }

    public void validatedAllowedCharacters(String value, String fieldName, String regex, HttpStatus status) {
        if (value != null && !value.matches(regex)) {
            String msg = MessageFormat.format(Constants.MSG_REGEX_INVALID_FIELDS, fieldName);
            log.warn(msg);
            throw new GeneralErrorException(Constants.MSG_VALIDATE_FIELD, status);
        }
    }
}
