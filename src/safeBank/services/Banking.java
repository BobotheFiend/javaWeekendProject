package safeBank.services;

import safeBank.data.models.Transaction;
import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.requests.WithdrawalRequest;
import safeBank.dtos.responses.DepositResponse;
import safeBank.dtos.responses.WithdrawalResponse;
import safeBank.exceptions.SafeBankException;

import java.math.BigDecimal;
import java.util.List;


public interface Banking {

    WithdrawalResponse withdraw(WithdrawalRequest request) throws SafeBankException;
    DepositResponse deposit(DepositRequest request) throws SafeBankException;
    List<Transaction> transactions(String accountNumber) throws SafeBankException;
    BigDecimal balance(String accountNumber) throws SafeBankException;
}
