package safeBank.exceptions;

public class InvalidPinException extends AuthAccountException {
    public InvalidPinException(String message) {
        super(message);
    }
    public InvalidPinException(String message, Throwable cause) {
        super(message, cause);
    }
}
