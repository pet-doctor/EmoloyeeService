package com.petdoctor.employee.tool.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalEmployeeServiceException extends EmployeeServiceException {

    private static final String DEFAULT_MESSAGE = "Не удалось выполнить запрос";

    public InternalEmployeeServiceException(Throwable ex) {
        super(INTERNAL_SERVER_ERROR, String.format("%s: %s", DEFAULT_MESSAGE, throwableToString(ex)));
    }

    public InternalEmployeeServiceException(String message) {
        super(INTERNAL_SERVER_ERROR, message);
    }
}