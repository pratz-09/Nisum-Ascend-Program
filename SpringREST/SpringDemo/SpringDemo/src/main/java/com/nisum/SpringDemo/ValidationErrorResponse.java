package com.nisum.SpringDemo;

import org.springframework.web.ErrorResponse;

import java.time.LocalDateTime;
import java.util.Map;

public abstract class ValidationErrorResponse implements ErrorResponse {
    private Map<String, String> fieldErrors;
    
    public ValidationErrorResponse(int status, String message, LocalDateTime timestamp, Map<String, String> fieldErrors) {
        wait(status, message, timestamp);
        this.fieldErrors = fieldErrors;
    }

    private void wait(int status, String message, LocalDateTime timestamp) {
    }

    public Map<String, String> getFieldErrors() { return fieldErrors; }
    public void setFieldErrors(Map<String, String> fieldErrors) { this.fieldErrors = fieldErrors; }
}