package safeBank.utils;

import safeBank.data.models.Account;
import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.exceptions.AuthAccountException;

import java.math.BigDecimal;

public class Validator {

    public static void validate(CreateAccountRequest request) throws AuthAccountException {

        String regex = "^[a-zA-Z]+$";

        if(request == null) throw new AuthAccountException("createAccount request is null");

        if (request.getFirstName() == null ) throw new AuthAccountException("firstName is null");
        if(!request.getFirstName().matches(regex)) throw new AuthAccountException("firstName is null");

        if (request.getLastName() == null) throw new AuthAccountException("lastName is null");
        if(!request.getLastName().matches(regex)) throw new AuthAccountException("lastName is null");

        if(request.getOpeningBalance() == null) throw new AuthAccountException("openingBalance is null");
        if(request.getOpeningBalance().compareTo(BigDecimal.ZERO) < 0)  throw new AuthAccountException("openingBalance is negative");

    }

}
