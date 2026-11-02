package safeBank.data.repositories;

import safeBank.data.models.Transaction;

import java.util.List;

public interface TransactionRepository {

    Transaction save(Transaction transaction);
    void delete(Transaction transaction);
    Transaction findByNumber(String number);
    List<Transaction> findAll(String number);
}
