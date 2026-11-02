package safeBank.data.repositories;

import safeBank.data.models.Account;

public interface AccountRepository {
    Account save(Account account);
    void delete(Account account);
    Account findByNumber(String number);

}
