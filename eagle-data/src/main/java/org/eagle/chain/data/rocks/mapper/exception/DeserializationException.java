package org.eagle.chain.data.rocks.mapper.exception;



/**
 * {@link DeserializationException}  is thrown when there is a problem with deserialization.
 */
public final class DeserializationException extends SerDeException {

    public DeserializationException(final String message) {
        super(message);
    }

    public DeserializationException(
            final String message,
            final Throwable throwable
    ) {
        super(message, throwable);
    }
}
