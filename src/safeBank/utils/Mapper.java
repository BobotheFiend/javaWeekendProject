package safeBank.utils;

import safeBank.data.models.Account;
import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.responses.CreateAccountResponse;

import java.util.Locale;

public class Mapper {

    public static Account map(CreateAccountRequest request){
        Account account = new Account();
        account.setFullName(request.getFirstName().toUpperCase(Locale.ROOT) + " " + request.getLastName().toUpperCase(Locale.ROOT));
        account.setBalance(request.getOpeningBalance());
        account.setPin(request.getPin());
        account.setNumber(request.getNumber());
        return account;
    }

    public static void map(CreateAccountResponse response, CreateAccountRequest request){
        response.setFirstName(request.getFirstName().toUpperCase(Locale.ROOT));
        response.setLastName(request.getLastName().toUpperCase(Locale.ROOT));
        response.setOpeningBalance(request.getOpeningBalance());
        response.setNumber(request.getNumber());
    }
}
