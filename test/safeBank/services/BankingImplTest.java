package safeBank.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import safeBank.data.models.Account;
import safeBank.data.repositories.AccountRepository;
import safeBank.data.repositories.AccountRepositoryImpl;
import safeBank.data.repositories.TransactionRepository;
import safeBank.data.repositories.TransactionRepositoryImpl;
import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.requests.WithdrawalRequest;
import safeBank.dtos.responses.CreateAccountResponse;
import safeBank.exceptions.*;

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

    public CreateAccountResponse createAccount() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setFirstName("John");
        request.setLastName("Bobo");
        request.setPin("1234");

        AuthAccount create = new AuthAccountImpl();
        try {
            return create.createAccount(request);
        } catch (AuthAccountException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void depositResquestIsNotNull(){

        assertThrows(InvalidAmountException.class, () ->
                banking.deposit(null)
                );
    }

    @ParameterizedTest(name = "Test {index} that input is negative {0} throws exception")
    @ValueSource(strings = {"-8900", "-5678", "-8800", "-1000000", "0"})
    public void depositInputIsNegative_throwsExceptionTest(String input){

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(input);

        assertThrows(InvalidAmountException.class, () ->
                banking.deposit(depositRequest)
        );
    }

    @Test
    public void createAccount_depositFunds_accountBalanceReflectsTest(){

        CreateAccountResponse account = createAccount();

        Account foundAccount = accountRepository.findByNumber(account.getNumber());

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount("10000");
        depositRequest.setAccountNumber(account.getNumber());


        System.out.println(foundAccount.getFullName());
        assertEquals(new BigDecimal(0), foundAccount.getBalance());
        try {
            banking.deposit(depositRequest);
            assertEquals(new BigDecimal(10_000), foundAccount.getBalance());

        }catch (SafeBankException e){
            e.printStackTrace();
        }


    }

    @Test
    public void createAccount_depositFundsTwice_accountReflectsTest(){

        CreateAccountResponse account = createAccount();

        Account foundAccount = accountRepository.findByNumber(account.getNumber());

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount("10000");
        depositRequest.setAccountNumber(account.getNumber());

        DepositRequest depositRequest2 = new DepositRequest();
        depositRequest2.setAmount("40000");
        depositRequest2.setAccountNumber(account.getNumber());

        assertEquals(new BigDecimal(0), foundAccount.getBalance());
        try {
            banking.deposit(depositRequest);
            assertEquals(new BigDecimal(10_000), foundAccount.getBalance());

            banking.deposit(depositRequest2);
            assertEquals(new BigDecimal(50_000), foundAccount.getBalance());

        }catch (SafeBankException e){
            e.printStackTrace();
        }

    }

    @Test
    public void createAccount_depositFunds_resposeIsNotNullTest(){

        CreateAccountResponse account = createAccount();

        Account foundAccount = accountRepository.findByNumber(account.getNumber());

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount("30000");
        depositRequest.setAccountNumber(account.getNumber());

        assertEquals(new BigDecimal(0), foundAccount.getBalance());
        try {
            assertNotEquals(null ,banking.deposit(depositRequest));

        }catch (SafeBankException e){
            e.printStackTrace();
        }

    }


    @Test
    public void withdrawRequestIsNotEmpty(){
        assertThrows(InvalidAmountException.class,
                () -> banking.withdraw(null)
        );
    }

    @ParameterizedTest(name = "Test {index} that input is negative {0} throws exception")
    @ValueSource(strings = {"-8900", "-5678", "-8800", "-1000000", "0"})
    public void withdrawAmountInputIsZeroAndBelow_throwsExceptionTest(String input){

        WithdrawalRequest request = new WithdrawalRequest();
        request.setAmount(input);

        assertThrows(InvalidAmountException.class, () ->
                banking.withdraw(request)
        );
    }


    @ParameterizedTest(name = "Test {index} that input is negative {0} throws exception")
    @CsvSource({
            "8900, ''",
            "5678, thrrr",
            "8800, 6ry",
            "1000000, 67ry"
    })

    private Account accountWithThirtyKay(){
        CreateAccountResponse acct = createAccount();
        Account foundAccount = accountRepository.findByNumber(acct.getNumber());

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount("30000");
        depositRequest.setAccountNumber(acct.getNumber());

        try{
            banking.deposit(depositRequest);
        }catch (SafeBankException e){
            e.printStackTrace();
        }
        return foundAccount;

    }
    public void withdrawAccountInputIsZeroAndBelow_throwsExceptionTest(String amountInput, String accountNumber){

        WithdrawalRequest request = new WithdrawalRequest();
        request.setAmount(amountInput);
        request.setAccountNumber(accountNumber);

        assertThrows(SafeBankException.class, () ->
                banking.withdraw(request)
        );
    }

    @Test
    public void createAccount_withdrawFundsOfTenKay_accountBalanceReflectsAsTwentyKayTest(){

        Account account = accountWithThirtyKay();

        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
        withdrawalRequest.setAccountNumber(account.getNumber());
        withdrawalRequest.setAmount("10000");

        try{
            banking.withdraw(withdrawalRequest);
            assertEquals(new BigDecimal(20_000), account.getBalance());
        }catch (SafeBankException e){
            e.printStackTrace();
        }

    }


    @Test
    public void createAccount_withdrawFundsOfTenKayTwice_accountBalanceReflectsAsTenKayTest(){

        Account account = accountWithThirtyKay();

        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
        withdrawalRequest.setAccountNumber(account.getNumber());
        withdrawalRequest.setAmount("10000");

        WithdrawalRequest withdrawalRequest2 = new WithdrawalRequest();
        withdrawalRequest2.setAccountNumber(account.getNumber());
        withdrawalRequest2.setAmount("10000");
        try{
            banking.withdraw(withdrawalRequest);
            assertEquals(new BigDecimal(20_000), account.getBalance());

            banking.withdraw(withdrawalRequest2);
            assertEquals(new BigDecimal(10_000), account.getBalance());

        }catch (SafeBankException e){
            e.printStackTrace();
        }

    }


    @Test
    public void createAccountWithThirtyKay_withdrawFundsOfFiftyKay_throwsExceptionTest(){

        Account account = accountWithThirtyKay();

        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
        withdrawalRequest.setAccountNumber(account.getNumber());
        withdrawalRequest.setAmount("50000");

        assertThrows(InsufficientFundsException.class, () ->
            banking.withdraw(withdrawalRequest)
       );

    }

    @Test
    public void createAccountWithTwoHundredKay_withdrawFundsOfOneHundredAndOneKay_throwsExceptionTest(){

        CreateAccountResponse acct = createAccount();
        Account foundAccount = accountRepository.findByNumber(acct.getNumber());

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount("200000");
        depositRequest.setAccountNumber(acct.getNumber());

        try{
            banking.deposit(depositRequest);
        }catch (SafeBankException e){
            e.printStackTrace();
        }


        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
        withdrawalRequest.setAccountNumber(foundAccount.getNumber());
        withdrawalRequest.setAmount("100001");

        assertThrows(WithdrawalLimitExceededException.class, () ->
                banking.withdraw(withdrawalRequest)
        );

    }




}