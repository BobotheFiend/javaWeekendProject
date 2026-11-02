package safeBank.services;

import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.responses.CreateAccountResponse;
import safeBank.exceptions.AuthAccountException;

import java.math.BigDecimal;

public interface AuthAccount {

    CreateAccountResponse createAccount(CreateAccountRequest request) throws AuthAccountException;
}
