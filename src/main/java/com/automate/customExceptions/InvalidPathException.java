package com.automate.customExceptions;

public class InvalidPathException extends FrameworkException {

    public InvalidPathException(String message) {
        super(message);
    }

    public InvalidPathException(String message, Throwable t) {
        super(message, t);
    }
}
