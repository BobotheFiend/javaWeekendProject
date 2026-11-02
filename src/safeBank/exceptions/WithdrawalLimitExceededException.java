package safeBank.exceptions;

public class WithdrawalLimitExceededException extends SafeBankException {
    public WithdrawalLimitExceededException(String message) {
        super(message);
    }
}
