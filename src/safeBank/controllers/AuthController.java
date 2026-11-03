package safeBank.controllers;

import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.requests.LoginRequest;
import safeBank.dtos.responses.CreateAccountResponse;
import safeBank.exceptions.AuthAccountException;
import safeBank.services.AuthAccount;
import safeBank.services.AuthAccountImpl;

public class AuthController {

    private static final AuthAccount authAccount = new AuthAccountImpl();

    public CreateAccountResponse register(CreateAccountRequest request) throws AuthAccountException {
        return authAccount.createAccount(request);
    }

    public String login(LoginRequest request) throws AuthAccountException {
        return authAccount.login(request);
    }

}
