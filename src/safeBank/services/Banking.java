package safeBank.services;

import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.requests.WithdrawalRequest;
import safeBank.dtos.responses.DespositResponse;
import safeBank.dtos.responses.WithdrawalResponse;
import safeBank.exceptions.BankingException;

public interface Banking {

    WithdrawalResponse withdraw(WithdrawalRequest request) throws BankingException;
    DespositResponse deposit(DepositRequest request) throws BankingException;
}
