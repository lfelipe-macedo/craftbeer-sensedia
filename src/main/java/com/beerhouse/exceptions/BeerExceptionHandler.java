package com.beerhouse.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@ControllerAdvice
public class BeerExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        BindingResult result = ex.getBindingResult();

        List<String> errors = new ArrayList<String>();

        result.getFieldErrors().forEach(error -> {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        });

        result.getGlobalErrors().forEach(error -> {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        });

        BeerException beerException = new BeerException(ex.getLocalizedMessage(), badRequest, errors);

        return new ResponseEntity<Object>(
                beerException, new HttpHeaders(), beerException.getStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex) {

        List<String> errors = new ArrayList<String>();

        ex.getConstraintViolations().forEach(error -> {
            errors.add(error.getRootBeanClass().getName() + " " +
                    error.getPropertyPath() + ": " + error.getMessage());
        });

        BeerException beerException =
                new BeerException(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, errors);

        return new ResponseEntity<Object>(
                beerException, new HttpHeaders(), beerException.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex) {
        String error =
                ex.getName() + " should be of type " + Objects.requireNonNull(ex.getRequiredType()).getName();

        BeerException beerException =
                new BeerException(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, error);

        return new ResponseEntity<Object>(
                beerException, new HttpHeaders(), beerException.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {


        BeerException beerException = new BeerException(ex.getMessage(), HttpStatus.NOT_FOUND, ex.getMessage());

        return new ResponseEntity<Object>(beerException, new HttpHeaders(), beerException.getStatus());
    }
}
