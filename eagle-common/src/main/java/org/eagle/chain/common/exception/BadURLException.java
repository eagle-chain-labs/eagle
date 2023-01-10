package org.eagle.chain.common.exception;

/**
 * BadURLException wraps errors due to malformed URLs.
 */
public class BadURLException extends EagleException {
  /**
   * Initializes exception with its message attribute.
   * @param message error message
   */
  public BadURLException(String message) {
    super(message);
  }
}
