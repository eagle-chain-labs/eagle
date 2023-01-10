package org.eagle.chain.common.exception;

/**
 * Base exception class for the Chain Core SDK.
 */
public class EagleException extends Exception {
    /**
     * Default constructor.
     */
    public EagleException() {
        super();
    }

    /**
     * Initializes exception with its message attribute.
     *
     * @param message error message
     */
    public EagleException(String message) {
        super(message);
    }

    /**
     * Initializes a new exception while storing the original cause.
     *
     * @param message the error message
     * @param cause   the cause of the exception
     */
    public EagleException(String message, Throwable cause) {
        super(message, cause);
    }
}
