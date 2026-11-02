package safeBank.data.repositories;

import org.junit.jupiter.api.Test;
import safeBank.data.models.Account;

import static org.junit.jupiter.api.Assertions.*;

public class AccountRepositoryImplTest {

    AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();

    @Test
    public void accountIsEmpty() {
        assertEquals(0, accountRepository.length());
    }
    @Test
    public void testThatICanSaveAnAccount() {
        Account account = new Account();
        accountRepository.save(account);
        assertEquals(1, accountRepository.length());

    }

    @Test
    public void testThatICanSaveTwoAccounts_lengthIsTwoTest() {
        Account account = new Account();
        Account account2 = new Account();

        accountRepository.save(account);
        accountRepository.save(account2);

        assertEquals(2, accountRepository.length());

    }

    @Test
    public void saveAccount_findAccountByNumber_checkThatNameMatchesTest() {
        Account account = new Account();
        account.setFullName("Sam Dam");
        Account savedAccount = accountRepository.save(account);
        assertEquals(1, accountRepository.length());

        Account foundAccount = accountRepository.findByNumber(savedAccount.getNumber());

        System.out.println("Found account " + foundAccount.getNumber());
        assertEquals(savedAccount.getFullName(), foundAccount.getFullName());
    }
}