package com.petdoctor.employee.handler;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.petdoctor.employee.model.response.Response;
import com.petdoctor.employee.tool.exception.EmployeeServiceException;
import com.petdoctor.employee.tool.exception.InternalEmployeeServiceException;
import com.petdoctor.employee.tool.exception.NotFoundEmployeeServiceException;
import com.petdoctor.employee.tool.exception.ValidationEmployeeServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Response> handleException(Throwable ex) {
        EmployeeServiceException exception;
        String status = "Error";

        if (ex instanceof EmployeeServiceException) {
            exception = (EmployeeServiceException) ex;

            if (ex instanceof NotFoundEmployeeServiceException) {
                status = "Not found";
            }
        } else if (ex instanceof MethodArgumentNotValidException ||
                ex instanceof MethodArgumentTypeMismatchException ||
                ex instanceof MissingRequestValueException ||
                ex instanceof ValidationException ||
                ex.getCause() instanceof MismatchedInputException) {
            exception = new ValidationEmployeeServiceException(ex);
        } else {
            exception = new InternalEmployeeServiceException(ex);
        }

        HttpStatus httpStatus = exception.getHttpStatus();
        Response response = Response.builder()
                .code(String.valueOf(httpStatus.value()))
                .status(status)
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(response, httpStatus);
    }
}