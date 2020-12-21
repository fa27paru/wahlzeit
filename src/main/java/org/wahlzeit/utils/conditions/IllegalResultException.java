package org.wahlzeit.utils.conditions;

public class IllegalResultException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public IllegalResultException() {
    }

    public IllegalResultException(String message) {
        super(message);
    }

    public IllegalResultException(Throwable cause) {
        super(cause);
    }

    public IllegalResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalResultException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
