package com.gucardev.trakyabilmuhbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class PermissionError extends RuntimeException {
    public PermissionError(String message) {
        super(message);
    }
}