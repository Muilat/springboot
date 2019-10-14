package com.example.demo.exception;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseEntity<ErrorDetails> handleObjectNotFoundException(ObjectNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false), ErrorDetails.NOT_FOUND_CODE);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                ex.getBindingResult().toString(), ErrorDetails.NOT_VALID_CODE);
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //MissingServletRequestParameterException: This exception is thrown when request missing parameter
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";

        ErrorDetails apiError =
                new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST.toString(), error, ErrorDetails.NOT_VALID_CODE);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    //This exception reports the result of constraint violations:
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ErrorDetails apiError =
                new ErrorDetails(new Date(), ex.getLocalizedMessage(),  errors.toString() , ErrorDetails.NOT_VALID_CODE);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    //This exception is thrown when method argument is not the expected type:
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ErrorDetails apiError =
                new ErrorDetails(new Date(), ex.getLocalizedMessage(),  error , ErrorDetails.NOT_VALID_CODE);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(
                " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        ErrorDetails apiError =
                new ErrorDetails(new Date(), ex.getLocalizedMessage(),  builder.toString() , ErrorDetails.NOT_VALID_CODE);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}