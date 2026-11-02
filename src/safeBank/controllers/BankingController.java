package safeBank.controllers;

import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.requests.WithdrawalRequest;
import safeBank.dtos.responses.DepositResponse;
import safeBank.dtos.responses.WithdrawalResponse;
import safeBank.exceptions.SafeBankException;
import safeBank.services.Banking;
import safeBank.services.BankingImpl;

public class BankingController {

    private final static Banking bank = new BankingImpl();

    public static DepositResponse deposit(DepositRequest request) throws SafeBankException {
        return bank.deposit(request);
    }

    public static WithdrawalResponse withdraw(WithdrawalRequest request) throws SafeBankException {
        return bank.withdraw(request);
    }
}
