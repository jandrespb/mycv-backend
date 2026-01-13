package com.jandcode.mycv.service.rules;

public enum CustomerFieldRule {
    /*
     * This class contains the basic rules of handle input text and text area:
     * Quantity of characters allowed and regex pattern to avoid special characters.
     *
     * */
    CUSTOMER("customer", 100, "^[A-Za-zÀ-ÿÑñ]+( [A-Za-zÀ-ÿÑñ]+)*$"),
    EMAIL("email", 320, "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"),
    MESSAGE("message", 500, "^(?!.* {2,})(?! )(?!.* $)[a-zA-ZÀ-ÿ0-9.,;?¿!\\-_\\$ ]+$");


    private final String fieldName;
    private final int maxLength;
    private final String regex;

    CustomerFieldRule(String fieldName, int maxLength, String regex) {
        this.fieldName = fieldName;
        this.maxLength = maxLength;
        this.regex = regex;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public String getRegex() {
        return regex;
    }
}
