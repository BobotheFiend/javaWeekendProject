package safeBank.services;

import safeBank.data.models.Account;
import safeBank.data.repositories.AccountRepository;
import safeBank.data.repositories.AccountRepositoryImpl;
import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.requests.LoginRequest;
import safeBank.dtos.responses.CreateAccountResponse;
import safeBank.exceptions.AuthAccountException;
import safeBank.utils.Mapper;


import static safeBank.utils.Mapper.map;
import static safeBank.utils.Validator.validate;

public class AuthAccountImpl implements AuthAccount {

    private final AccountRepository accountRepository = new AccountRepositoryImpl();


    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) throws AuthAccountException{
        validate(request);
        Account account = accountRepository.save(map(request));
        CreateAccountResponse response = new CreateAccountResponse();
        map(response, account);
        return response;
    }

    @Override
    public String login(LoginRequest loginRequest) throws AuthAccountException {
        validate(loginRequest);
        Account foundAccount = accountRepository.findByNumber(loginRequest.getAccountNumber());
        if(foundAccount == null) throw new AuthAccountException("Account not found");
        if(!foundAccount.getPin().equals(loginRequest.getPin())) throw new AuthAccountException("Invalid input!");
        if(!foundAccount.getNumber().equals(loginRequest.getAccountNumber())) throw new AuthAccountException("Invalid input!");

        foundAccount.setLoggedIn(true);
        accountRepository.save(foundAccount);

        return "Success";
    }



}
