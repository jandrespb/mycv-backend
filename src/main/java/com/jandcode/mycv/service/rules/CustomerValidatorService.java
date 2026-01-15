package com.jandcode.mycv.service.rules;

import com.jandcode.mycv.dao.CustomerDataSource;
import com.jandcode.mycv.entity.Customer;
import com.jandcode.mycv.exception.GeneralErrorException;
import com.jandcode.mycv.utils.Constants;
import com.jandcode.mycv.utils.FormWebUtils;
import com.jandcode.mycv.utils.HtmlSanitizerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class CustomerValidatorService {
    private static final Logger log = LoggerFactory.getLogger(CustomerValidatorService.class);

    private final CustomerDataSource customerDataSource;
    private final FormWebUtils formWebUtils = new FormWebUtils();

    public CustomerValidatorService(CustomerDataSource customerDataSource) {
        this.customerDataSource = customerDataSource;
    }

    public void validateCustomer(Customer customer) {

        // Rule 1: Maximum Records Validation
        validateMaxRecords();

        for (CustomerFieldRule rule : CustomerFieldRule.values()) {

            String rawValue = extractFieldValue(customer, rule.getFieldName());

            // 1️⃣ Normalización (esto es lo que valida el regex)
            String normalizedValue = rawValue == null ? "" : rawValue.trim();

            if ("email".equals(rule.getFieldName())) {
                normalizedValue = normalizedValue.toLowerCase();
            }

            log.debug("FIELD {} RAW='{}' NORMALIZED='{}'",
                    rule.getFieldName(), rawValue, normalizedValue);

            // 2️⃣ Validaciones sobre el valor NORMALIZADO
            formWebUtils.validatedEmptyText(
                    normalizedValue,
                    MessageFormat.format(Constants.MSG_EMPTY_FIELDS, rule.getFieldName()),
                    HttpStatus.BAD_REQUEST
            );

            formWebUtils.validatedMaxLength(
                    normalizedValue,
                    rule.getFieldName(),
                    rule.getMaxLength(),
                    HttpStatus.BAD_REQUEST
            );

            formWebUtils.validatedAllowedCharacters(
                    normalizedValue,
                    rule.getFieldName(),
                    rule.getRegex(),
                    HttpStatus.BAD_REQUEST
            );

            // 3️⃣ Sanitización SOLO para persistencia
            String sanitizedValue;

            if ("email".equals(rule.getFieldName())) {
                sanitizedValue = normalizedValue;
            } else {
                sanitizedValue = HtmlSanitizerUtil.sanitize(normalizedValue);
            }

            log.debug("FIELD {} SANITIZED_FOR_STORAGE='{}'",
                    rule.getFieldName(), sanitizedValue);

            // 4️⃣ Asignar valor final al objeto
            assignSanitizedValue(customer, rule.getFieldName(), sanitizedValue);
        }
    }

    /**
     * Rule: Maximum Records Validation
     * Validates that the number of Customer records does not exceed the maximum allowed.
     * Throws a GeneralErrorException if the limit is exceeded.
     *
     * note: This records is on base of database records, not per user.
     */
    private void validateMaxRecords() {

        int maxAllowedRecords = 100;
        long currentCount = customerDataSource.count();

        if (currentCount >= maxAllowedRecords) {
            log.warn(Constants.MSG_MAX_RECORDS_EXCEEDED);
            throw new GeneralErrorException(Constants.MSG_RATE, HttpStatus.TOO_MANY_REQUESTS);
        }
    }

    /**
     * Rule: IP Address Rules Validation
     * Validates the rules related to the IP address from which the Customer is being created.
     * - Maximum of 2 records allowed from the same IP.
     * - No duplicate email addresses allowed from the same IP.
     *
     * @param customer  The Customer object being validated.
     * @param ipAddress The IP address from which the Customer is being created.
     */
    public void validateIpCustomer(Customer customer, String ipAddress) {
        List<Customer> customersFromIp = customerDataSource.findByIp(ipAddress);

        byte quantityOfRecords = 2;

        if (customersFromIp.size() >= quantityOfRecords) {
            log.warn(MessageFormat.format(Constants.MSG_IP_EMAIL_REGISTER, quantityOfRecords));
            throw new GeneralErrorException(
                    Constants.MSG_RATE,
                    HttpStatus.TOO_MANY_REQUESTS
            );
        }

        boolean sameEmailUsed = customersFromIp.stream()
                .anyMatch(c -> c.getEmail().equalsIgnoreCase(customer.getEmail()));

        if (sameEmailUsed) {
            log.warn(Constants.MSG_SAME_EMAIL);
            throw new GeneralErrorException(
                    Constants.MSG_VALIDATE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    /**
     * Rule: Unique Email Validation
     * Validates that the email address of the Customer is unique across all records.
     * Throws a GeneralErrorException if the email is already registered.
     *
     * @param customer The Customer object being validated.
     */
    public void validateUniqueEmail(Customer customer) {
        boolean exists = customerDataSource.existsByEmail(customer.getEmail());
        if (exists) {
            log.warn(Constants.MSG_SAME_EMAIL);
            throw new GeneralErrorException(
                    Constants.MSG_VALIDATE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    /**
     * Rule Helper Method
     * Extracts the value of a specified field from a Customer object.
     *
     * @param customer  The Customer object.
     * @param fieldName The name of the field to extract.
     * @return The value of the specified field, or an empty string if the field is null or does not exist.
     */
    private String extractFieldValue(Customer customer, String fieldName) {
        return switch (fieldName) {
            case "customer" -> customer.getCustomer() == null ? "" : customer.getCustomer();
            case "email" -> customer.getEmail() == null ? "" : customer.getEmail();
            case "message" -> customer.getMessage() == null ? "" : customer.getMessage();
            default -> "";
        };
    }

    /**
     * Assigns the sanitized value back to the corresponding field in the Customer object.
     *
     * @param customer       The Customer object.
     * @param fieldName      The name of the field to assign the value to.
     * @param sanitizedValue The sanitized value to assign.
     */
    private void assignSanitizedValue(Customer customer, String fieldName, String sanitizedValue) {
        switch (fieldName) {
            case "customer" -> customer.setCustomer(sanitizedValue);
            case "email" -> customer.setEmail(sanitizedValue);
            case "message" -> customer.setMessage(sanitizedValue);
        }
    }
}
