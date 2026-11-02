package safeBank.services;

import safeBank.data.models.Account;
import safeBank.data.repositories.AccountRepository;
import safeBank.data.repositories.AccountRepositoryImpl;
import safeBank.data.repositories.TransactionRepository;
import safeBank.data.repositories.TransactionRepositoryImpl;
import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.requests.WithdrawalRequest;
import safeBank.dtos.responses.DespositResponse;
import safeBank.dtos.responses.WithdrawalResponse;
import safeBank.exceptions.BankingException;

import java.math.BigDecimal;

public class BankingImpl implements Banking {

    private final AccountRepository accountRepository =  new AccountRepositoryImpl();
    private final TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    @Override
    public WithdrawalResponse withdraw(WithdrawalRequest request) throws BankingException {

        return null;
    }

    @Override
    public DespositResponse deposit(DepositRequest request)throws  BankingException {
        validate(request);

        Account foundAccount = accountRepository.findByNumber(request.getAccountNumber());
        if (foundAccount == null) throw new BankingException("Account not found");

        foundAccount.setBalance(new BigDecimal(request.getAmount()));

        accountRepository.save(foundAccount);

        return null;
    }

    private void validate(DepositRequest request) throws BankingException {
        if(request == null) throw new BankingException("DepositRequest cannot be null");

        if(request.getAmount() == null) throw new BankingException("DepositRequest cannot be null");
        if(request.getAccountNumber() == null) throw new BankingException("DepositRequest cannot be null");

        BigDecimal amount = new BigDecimal(request.getAmount());
        if(amount.compareTo(BigDecimal.ZERO) < 0) throw new BankingException("Amount cannot be negative");
        if(amount.compareTo(BigDecimal.ZERO) == 0) throw new BankingException("Please Deposit An amount Higher than 0");
    }
}
