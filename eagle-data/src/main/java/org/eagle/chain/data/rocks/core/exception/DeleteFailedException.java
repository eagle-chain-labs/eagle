package org.eagle.chain.data.rocks.core.exception;

/**
 * {@link DeleteFailedException} is thrown when there is a problem with deleting an entity from a repository.
 */
public final class DeleteFailedException extends RocksIOException {

    public DeleteFailedException(final String message) {
        super(message);
    }

    public DeleteFailedException(
            final String message,
            final Throwable throwable
    ) {
        super(message, throwable);
    }
}
