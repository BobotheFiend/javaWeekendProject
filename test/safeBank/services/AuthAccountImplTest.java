package safeBank.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.responses.CreateAccountResponse;
import safeBank.exceptions.AuthAccountException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthAccountImplTest {

    AuthAccountImpl authAccount;
    @BeforeEach
    public void setUp() {
        authAccount = new AuthAccountImpl();
    }


    @Test
    public void nullRequestThrowsErrorTest() {

        assertThrows(AuthAccountException.class,
                () -> authAccount.createAccount(null)
        );
    }

    @ParameterizedTest()
    @NullAndEmptySource
    @ValueSource(strings = {"jim4", "746", "777&&QYW", "  ", " ","=+EYRY"})
    public void invalidFirstnameRequestThrowsErrorTest(String firstname) {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setFirstName(firstname);

        assertThrows(AuthAccountException.class,
                () -> authAccount.createAccount(createAccountRequest)
        );
    }

    @ParameterizedTest()
    @ValueSource(strings = {"jimbo", "Hator", "Himothy"})
    public void validFirstNameRequestTest(String firstname) {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setFirstName(firstname);

        try{
            authAccount.createAccount(createAccountRequest);
            assertEquals(firstname, createAccountRequest.getFirstName());

        }catch(AuthAccountException e) {
            System.err.println(e.getMessage());
        }

    }


    @ParameterizedTest()
    @CsvSource({
            " Mary, Mary",
            "David , David",
            "Java  , Java"
    })
    public void firstNameRequestWithTrailingWithSpacesIsValidTest(String firstname, String expected) {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setFirstName(firstname);

        try{
            authAccount.createAccount(createAccountRequest);
            assertEquals(expected, createAccountRequest.getFirstName());

        }catch(AuthAccountException e) {
            System.err.println(e.getMessage());
        }

    }
}