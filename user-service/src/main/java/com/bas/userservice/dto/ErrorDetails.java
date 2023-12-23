package com.bas.userservice.dto;

import java.util.Date;

public class ErrorDetails {
    private Date timseStamp;
    private String message;
    private String details;

    public ErrorDetails(Date timseStamp, String message, String details) {
        this.timseStamp = timseStamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimseStamp() {
        return timseStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
