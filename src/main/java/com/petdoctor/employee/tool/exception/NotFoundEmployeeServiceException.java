package com.petdoctor.employee.tool.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundEmployeeServiceException extends EmployeeServiceException {

    public NotFoundEmployeeServiceException(Throwable ex) {
        super(NOT_FOUND, throwableToString(ex));
    }

    public NotFoundEmployeeServiceException(String message) {
        super(NOT_FOUND, message);
    }
}