package com.javaweb.customexception;

public class InvalidParamException extends Exception {
    public InvalidParamException(String message) {
        super(message);
    }
}
