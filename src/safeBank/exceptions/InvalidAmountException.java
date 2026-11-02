package safeBank.exceptions;

public class InvalidAmountException extends SafeBankException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
