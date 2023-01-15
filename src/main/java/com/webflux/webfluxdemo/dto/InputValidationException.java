package com.webflux.webfluxdemo.dto;

/**
 * Custom Exception class
 */
public class InputValidationException extends RuntimeException {

    private static final String MSG = "allow in range 10 -- 20";
    private static final int errorCode = 100;
    public  final int input;

    public InputValidationException(int input) {
        super(MSG);
        this.input = input;
    }

    public int getInput() {
        return input;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
