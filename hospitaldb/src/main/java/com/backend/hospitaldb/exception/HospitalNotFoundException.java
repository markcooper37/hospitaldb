package com.backend.hospitaldb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HospitalNotFoundException extends RuntimeException {
    public HospitalNotFoundException(String message) {
        super(message);
    }
}
