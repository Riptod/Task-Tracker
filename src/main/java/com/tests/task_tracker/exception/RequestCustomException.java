package com.tests.task_tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequestCustomException extends RuntimeException {
    public RequestCustomException(String message) {
        super(message);
    }
}