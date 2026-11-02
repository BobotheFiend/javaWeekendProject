package safeBank.data.repositories;

import safeBank.data.models.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryImpl implements AccountRepository {

    private static Map<String, Account> accounts = new HashMap<>();
    private static int GENERATED_ACCOUNT_NUMBER;

    @Override
    public Account save(Account account) {
        generateAccountNumber();
        account.setNumber(String.valueOf(GENERATED_ACCOUNT_NUMBER));
        accounts.put(account.getNumber(), account);
        return account;
    }

    @Override
    public void delete(Account account) {
        accounts.remove(account.getNumber());
    }

    @Override
    public Account findByNumber(String number) {
        return accounts.get(number);
    }

    @Override
    public int length() {
        return accounts.size();
    }

    private void generateAccountNumber() {
        ++GENERATED_ACCOUNT_NUMBER;
    }
}
