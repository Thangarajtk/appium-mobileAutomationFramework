package com.automate.customExceptions;

public class PropertyFileUsageException extends FrameworkException {

    public PropertyFileUsageException(String message) {
        super(message);
    }

    public PropertyFileUsageException(String message, Throwable t) {
        super(message, t);
    }
}
