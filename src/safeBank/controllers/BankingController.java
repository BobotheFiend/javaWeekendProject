package safeBank.controllers;

import safeBank.data.models.Transaction;
import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.requests.WithdrawalRequest;
import safeBank.dtos.responses.DepositResponse;
import safeBank.dtos.responses.WithdrawalResponse;
import safeBank.exceptions.SafeBankException;
import safeBank.services.Banking;
import safeBank.services.BankingImpl;

import java.math.BigDecimal;
import java.util.List;

public class BankingController {

    private final static Banking bank = new BankingImpl();

    public  DepositResponse deposit(DepositRequest request) throws SafeBankException {
        return bank.deposit(request);
    }

    public  WithdrawalResponse withdraw(WithdrawalRequest request) throws SafeBankException {
        return bank.withdraw(request);
    }

    public List<Transaction> transactions(String accountNumber) throws SafeBankException {
        return bank.transactions(accountNumber);
    }

    public BigDecimal balance(String accountNumber) throws SafeBankException {
        return bank.balance(accountNumber);
    }

}
