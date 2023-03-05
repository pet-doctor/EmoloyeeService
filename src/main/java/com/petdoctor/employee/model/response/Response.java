package com.petdoctor.employee.model.response;

import lombok.Builder;

@Builder
public class Response {
        public String code;
        public String status;
        public String message;
}