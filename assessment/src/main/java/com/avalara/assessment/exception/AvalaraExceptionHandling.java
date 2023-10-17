package com.avalara.assessment.exception;

import com.avalara.assessment.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AvalaraExceptionHandling {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponse> exception(BadRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("INVALID REQUEST");
        errorResponse.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Internal Server Error");
        errorResponse.setErrorMessage("Try Again after some time");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
