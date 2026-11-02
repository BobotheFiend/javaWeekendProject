package safeBank.utils;

import safeBank.data.models.Account;
import safeBank.data.models.Transaction;
import safeBank.data.models.enums.TransactionType;
import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.responses.CreateAccountResponse;
import safeBank.dtos.responses.DepositResponse;
import safeBank.dtos.responses.WithdrawalResponse;

import java.math.BigDecimal;

public class Mapper {

    public static Account map(CreateAccountRequest request){
        Account account = new Account();
        account.setFullName(request.getFirstName().toUpperCase() + " " + request.getLastName().toUpperCase());
        account.setBalance(new BigDecimal(0));
        account.setPin(request.getPin());
        return account;
    }

    public static void map(CreateAccountResponse response, Account request){
        response.setFullName(request.getFullName());
        response.setOpeningBalance(request.getBalance());
        response.setNumber(request.getNumber());
    }

    public static  Transaction map(Transaction transaction, Account foundAccount, DepositResponse response){
        transaction.setAccountNumber(foundAccount.getNumber());
        transaction.setDateTime(response.getDateTime());
        transaction.setType(TransactionType.CREDIT);
        return transaction;
    }

    public static Transaction map(Transaction transaction, Account account, WithdrawalResponse response){
        transaction.setAccountNumber(account.getNumber());
        transaction.setDateTime(response.getDateTime());
        transaction.setType(TransactionType.DEBIT);
        return transaction;
    }
}
