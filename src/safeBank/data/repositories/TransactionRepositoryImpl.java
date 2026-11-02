package safeBank.data.repositories;

import safeBank.data.models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepositoryImpl implements TransactionRepository {

    private Map<String,Transaction> transactions = new HashMap<>();
    private int GENERATED_ID = 0;

    @Override
    public Transaction save(Transaction transaction) {
        generateId();
        transaction.setId(String.valueOf(GENERATED_ID));
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }

    @Override
    public void delete(Transaction transaction) {
        transactions.remove(transaction.getId());
    }

    @Override
    public Transaction findByNumber(String id) {
        return  transactions.get(id);
    }

    @Override
    public List<Transaction> findAll(String number){
        List<Transaction> list = new ArrayList<>();

        for(Transaction transaction : transactions.values()){
            if(number.equals(transaction.getAccountNumber())) list.add(transaction);
        }
        return list;
    }

    private void generateId(){
        GENERATED_ID++;
    }

}
