package safeBank.exceptions;

public class AuthAccountException extends SafeBankException {
    public AuthAccountException(String message) {
        super(message);
    }
    public AuthAccountException(String message, Throwable cause) {
        super(message, cause);
    }
    public AuthAccountException(Throwable cause) {
        super(cause);
    }

}
