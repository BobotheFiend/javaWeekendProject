package safeBank.exceptions;

public class WithdrawalLimitExceededException extends BankingException {
    public WithdrawalLimitExceededException(String message) {
        super(message);
    }
}
