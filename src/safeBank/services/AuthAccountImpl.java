package safeBank.services;

import safeBank.data.models.Account;
import safeBank.data.repositories.AccountRepository;
import safeBank.data.repositories.AccountRepositoryImpl;
import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.responses.CreateAccountResponse;
import safeBank.exceptions.AuthAccountException;
import safeBank.utils.Mapper;


import static safeBank.utils.Validator.validate;

public class AuthAccountImpl implements AuthAccount {

    private AccountRepository accountRepository = new AccountRepositoryImpl();


    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) throws AuthAccountException{
        validate(request);

        Account foundAccount = accountRepository.findByNumber(request.getNumber());
        if (foundAccount != null) throw new AuthAccountException("Account already exists");

        Account account = Mapper.map(request);
        accountRepository.save(account);

        CreateAccountResponse response = new CreateAccountResponse();
        Mapper.map(response, request);

        return response;
    }


}
