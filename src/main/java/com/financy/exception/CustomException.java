package com.financy.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

    public CustomException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_ACCEPTABLE;
    }
}
