package safeBank.data.repositories;

import safeBank.data.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    private List<Account> accounts = new ArrayList<>();


    @Override
    public Account save(Account account) {
        accounts.add(account);
        return account;
    }

    @Override
    public void delete(Account account) {
        accounts.remove(account);
    }

    @Override
    public Account findByNumber(String number) {
        for (Account account : accounts) {
            if (account.getNumber().equals(number)) return account;
        }
        return null;
    }
}
