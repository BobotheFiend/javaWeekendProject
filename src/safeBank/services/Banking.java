package safeBank.services;

import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.requests.WithdrawalRequest;
import safeBank.dtos.responses.DepositResponse;
import safeBank.dtos.responses.WithdrawalResponse;
import safeBank.exceptions.SafeBankException;


public interface Banking {

    WithdrawalResponse withdraw(WithdrawalRequest request) throws SafeBankException;
    DepositResponse deposit(DepositRequest request) throws SafeBankException;
}
