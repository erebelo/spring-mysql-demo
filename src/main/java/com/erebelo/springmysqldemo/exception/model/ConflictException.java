package com.erebelo.springmysqldemo.exception.model;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
