package com.example.config.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) ex).getConstraintViolations();

        List<ErrorMessage> errorMessages = constraintViolations.stream().map(constraintViolation ->
                new ErrorMessage(constraintViolation.getPropertyPath().toString(),
                constraintViolation.getInvalidValue() == null ? null: constraintViolation.getInvalidValue().toString()
                , constraintViolation.getMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<Object>(errorMessages, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
