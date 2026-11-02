package safeBank.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import safeBank.data.models.Account;
import safeBank.data.repositories.AccountRepository;
import safeBank.data.repositories.AccountRepositoryImpl;
import safeBank.data.repositories.TransactionRepository;
import safeBank.data.repositories.TransactionRepositoryImpl;
import safeBank.dtos.requests.DepositRequest;
import safeBank.exceptions.BankingException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BankingImplTest {

    AccountRepository accountRepository;
    TransactionRepository transactionRepository;

    Banking banking;
    @BeforeEach
    public void setup() {
        accountRepository = new AccountRepositoryImpl();
        transactionRepository = new TransactionRepositoryImpl();
        banking = new BankingImpl();
    }

    public Account createAccount() {
        Account account = new Account();
        account.setFullName("Testing Test");
        account.setBalance(new BigDecimal(0));
        account.setPin("0000");
        account.setNumber("21089000045");
        return account;
    }

    @Test
    public void depositResquestIsNotNull(){

        assertThrows(BankingException.class, () ->
                banking.deposit(null)
                );
    }

    @ParameterizedTest(name = "Test {index} that input is negative {0} throws exception")
    @ValueSource(strings = {"-8900", "-5678", "-8800", "-1000000", "0"})
    public void depositInputIsNegative_throwsExceptionTest(String input){

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(input);

        assertThrows(BankingException.class, () ->
                banking.deposit(depositRequest)
        );
    }

    @ParameterizedTest()
    public void createAccount_depositFunds_accountBalanceReflectsTest(){

        createAccount();

    }


}