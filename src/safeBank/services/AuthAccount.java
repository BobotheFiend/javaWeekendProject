package safeBank.services;

import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.requests.LoginRequest;
import safeBank.dtos.responses.CreateAccountResponse;
import safeBank.exceptions.AuthAccountException;

import java.math.BigDecimal;

public interface AuthAccount {

    CreateAccountResponse createAccount(CreateAccountRequest request) throws AuthAccountException;

    String login(LoginRequest loginRequest) throws AuthAccountException;
}
