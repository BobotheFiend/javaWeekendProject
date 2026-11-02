package safeBank.services;

import safeBank.data.models.Account;
import safeBank.data.models.Transaction;
import safeBank.data.repositories.AccountRepository;
import safeBank.data.repositories.AccountRepositoryImpl;
import safeBank.data.repositories.TransactionRepository;
import safeBank.data.repositories.TransactionRepositoryImpl;
import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.requests.WithdrawalRequest;
import safeBank.dtos.responses.DepositResponse;
import safeBank.dtos.responses.WithdrawalResponse;
import safeBank.exceptions.InsufficientFundsException;
import safeBank.exceptions.InvalidAmountException;
import safeBank.exceptions.SafeBankException;
import safeBank.exceptions.WithdrawalLimitExceededException;
import safeBank.utils.Mapper;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static safeBank.utils.Validator.validate;

public class BankingImpl implements Banking {

    private final AccountRepository accountRepository =  new AccountRepositoryImpl();
    private final TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    @Override
    public WithdrawalResponse withdraw(WithdrawalRequest request) throws SafeBankException{

        validate(request);

        Account foundAccount = accountRepository.findByNumber(request.getAccountNumber());
        if (foundAccount == null) throw new InvalidAmountException("Account not found");
        BigDecimal amountInBank = foundAccount.getBalance();
        BigDecimal amountToWithdraw = new BigDecimal(request.getAmount());
        if(amountInBank.compareTo(amountToWithdraw) < 0) throw new InsufficientFundsException("Insufficient Funds!");

        if(amountToWithdraw.compareTo(new BigDecimal(100_000)) > 0) throw new WithdrawalLimitExceededException("You can only withdraw 100_000 as one withdrawal session!");
        foundAccount.setBalance(foundAccount.getBalance().subtract(new BigDecimal(request.getAmount())));

        accountRepository.save(foundAccount);

        WithdrawalResponse withdrawalResponse = new WithdrawalResponse();
        withdrawalResponse.setDateTime(LocalDateTime.now(Clock.systemUTC()));
        withdrawalResponse.setAmountWithdrew(request.getAmount());

        Transaction transaction = new Transaction();
        transactionRepository.save(Mapper.map(transaction, foundAccount, withdrawalResponse));
        return withdrawalResponse;
    }

    @Override
    public DepositResponse deposit(DepositRequest request)throws SafeBankException {
        validate(request);

        Account foundAccount = accountRepository.findByNumber(request.getAccountNumber());
        if (foundAccount == null) throw new InvalidAmountException("Account not found");

        foundAccount.setBalance(foundAccount.getBalance().add(new BigDecimal(request.getAmount())));
        accountRepository.save(foundAccount);


        DepositResponse response = new DepositResponse();
        response.setAmountDeposited(request.getAmount());
        response.setDateTime(LocalDateTime.now(Clock.systemDefaultZone()));

        Transaction transaction = new Transaction();
        transactionRepository.save(Mapper.map(transaction, foundAccount, response));

        return response;

    }

}
