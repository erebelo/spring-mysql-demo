package com.erebelo.springmysqldemo.exception.model;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
