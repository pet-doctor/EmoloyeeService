package com.petdoctor.employee.tool.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ValidationEmployeeServiceException extends EmployeeServiceException {

    private static final String DEFAULT_MESSAGE = "Некорректный запрос";

    public ValidationEmployeeServiceException(Throwable ex) {
        super(BAD_REQUEST, String.format("%s: %s", DEFAULT_MESSAGE, throwableToString(ex)));
    }

    public ValidationEmployeeServiceException(String message) {
        super(BAD_REQUEST, message);
    }
}