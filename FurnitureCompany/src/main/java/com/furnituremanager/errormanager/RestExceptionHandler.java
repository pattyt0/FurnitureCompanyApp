package com.furnituremanager.errormanager;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public RestExceptionHandler(){
        super();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), error, ex));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorMessage apiError = new ErrorMessage(HttpStatus.NOT_FOUND.toString());
        apiError.setDetail(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage) {
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(Integer.valueOf(errorMessage.getStatus())));
    }
}
