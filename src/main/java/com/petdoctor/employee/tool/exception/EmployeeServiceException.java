package com.petdoctor.employee.tool.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmployeeServiceException extends RuntimeException {

    private final HttpStatus httpStatus;

    protected EmployeeServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    protected static String throwableToString(Throwable t) {
        return String.format("%s: %s%s",
                t.getClass().getSimpleName(),
                t.getMessage(),
                t.getCause() == null ? "" : ", cause: " + t.getCause());
    }
}
