package safeBank.data.repositories;

import safeBank.data.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();
    @Override
    public Transaction save(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public void delete(Transaction transaction) {
        transactions.remove(transaction);
    }

    @Override
    public Transaction findByNumber(String number) {

        for (Transaction transaction : transactions) {
            if (number.equals(transaction.getAccountNumber())) return transaction;
        }
        return null;
    }

    @Override
    public List<Transaction> findAll(String number){
        List<Transaction> foundTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (number.equals(transaction.getAccountNumber())) foundTransactions.add(transaction);
        }
        return foundTransactions;
    }
}
