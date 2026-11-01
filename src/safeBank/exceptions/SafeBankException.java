package safeBank.exceptions;

public class SafeBankException extends Throwable {
    public SafeBankException(String message) {
        super(message);
    }
    public SafeBankException(String message, Throwable cause) {
      super(message, cause);
    }
    public SafeBankException(Throwable cause) {
      super(cause);
    }
}
