package safeBank.exceptions;

public class BankingException extends SafeBankException {
    public BankingException(String message) {
        super(message);
    }
    public BankingException(String message, Throwable cause) {
        super(message, cause);
    }
    public BankingException(Throwable cause) {
        super(cause);
    }

}
