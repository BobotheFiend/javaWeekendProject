package safeBank.exceptions;

public class InsufficientFundsException extends SafeBankException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
