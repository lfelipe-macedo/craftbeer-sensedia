package com.beerhouse.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BeerException {
    private final String message;
    private final HttpStatus status;
    private final List<String> errors;

    public BeerException(String message, HttpStatus status, List<String> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    public BeerException(String message, HttpStatus status, String error) {
        this.message = message;
        this.status = status;
        this.errors = Collections.singletonList(error);
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }


}
