package com.jandcode.mycv.entity.response;

public class GeneralErrorResponse {

    private final int status;
    private final String message;
    private final long timeStamp;

    public GeneralErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timeStamp = System.currentTimeMillis();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
