package com.jandcode.mycv.entity.response;

import java.time.LocalDateTime;

public class GeneralSuccessResponse {

    private final String message;
    private final int status;
    private final LocalDateTime createdAt;

    public GeneralSuccessResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
