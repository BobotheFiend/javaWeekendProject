package safeBank.services;

import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.responses.CreateAccountResponse;

import java.math.BigDecimal;

public interface AuthAccount {

    CreateAccountResponse createAccount(CreateAccountRequest request);
}
