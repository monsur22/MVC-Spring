package com.example.mvc.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException () {
        super("Not Found Exception");
    }

}
