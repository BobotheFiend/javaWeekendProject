package safeBank.utils;

import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.requests.LoginRequest;
import safeBank.dtos.requests.WithdrawalRequest;
import safeBank.exceptions.AuthAccountException;
import safeBank.exceptions.InvalidAmountException;
import safeBank.exceptions.InvalidPinException;
import safeBank.exceptions.SafeBankException;

import java.math.BigDecimal;

public class Validator {

    private static final String regex = "^[a-zA-Z]+$";
    private static final String pinRegex = "^[0-9]{4}$";
    private static final String accountRegex = "^[0-9]+$";
    private static final String amountRegex = "^[1-9]\\d*(?:\\.\\d{1,2})?$";

    public static void validate(CreateAccountRequest request) throws  AuthAccountException{



        if(request == null) throw new AuthAccountException("createAccount request is null");

        if(request.getPin() == null) throw new AuthAccountException("pin is null");
        if (request.getFirstName() == null ) throw new AuthAccountException("firstName is null");
        if(!request.getFirstName().matches(regex)) throw new AuthAccountException("Require an All Alphabetic Input");

        if (request.getLastName() == null) throw new AuthAccountException("lastName is null");
        if(!request.getLastName().matches(regex)) throw new AuthAccountException("Require an All Alphabetic Input");

        if(!request.getPin().matches(pinRegex)) throw new InvalidPinException("Pin is to be four digits");

    }

    public static void validate(DepositRequest request) throws SafeBankException {
        if(request == null) throw new InvalidAmountException("DepositRequest cannot be null");

        if(request.getAmount() == null) throw new InvalidAmountException("DepositRequest cannot be null");
        if(request.getAccountNumber() == null) throw new InvalidAmountException("DepositRequest cannot be null");

        if(!request.getAmount().matches(amountRegex)) throw new InvalidAmountException("Amount is not a digit");
        BigDecimal amount = new BigDecimal(request.getAmount());
        if(amount.compareTo(BigDecimal.ZERO) < 0) throw new InvalidAmountException("Amount cannot be negative");
        if(amount.compareTo(BigDecimal.ZERO) == 0) throw new InvalidAmountException("Please Deposit An amount Higher than 0");
    }


    public static void validate(WithdrawalRequest request) throws SafeBankException {
        if(request == null) throw new InvalidAmountException("WithdrawalRequest cannot be null");
        if(request.getAccountNumber() == null) throw new InvalidAmountException("WithdrawalRequest cannot be null");
        if(request.getAmount() == null) throw new InvalidAmountException("WithdrawalRequest cannot be null");


        BigDecimal amount = new BigDecimal(request.getAmount());
        if(amount.compareTo(BigDecimal.ZERO) < 0)  throw new InvalidAmountException("Amount cannot be negative");
        if(amount.compareTo(BigDecimal.ZERO) == 0) throw new InvalidAmountException("Please Deposit An Amount Higher than 0");

        if(!request.getAccountNumber().matches(accountRegex)) throw new AuthAccountException("Account number is invalid");



    }

    public static void validate(LoginRequest request) throws AuthAccountException {
        if(request == null) throw new AuthAccountException("LoginRequest cannot be null");
        if(request.getAccountNumber() == null) throw new AuthAccountException("LoginRequest cannot be null");
        if(request.getPin() == null) throw new AuthAccountException("LoginRequest cannot be null");

        if(!request.getAccountNumber().matches(accountRegex)) throw new AuthAccountException("Account number is invalid");
        if(!request.getPin().matches(pinRegex)) throw new AuthAccountException("Pin is to be four digits");

    }
}

