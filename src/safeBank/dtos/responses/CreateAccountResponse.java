package safeBank.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountResponse {
    String firstName;
    String lastName;
    BigDecimal openingBalance;
    String number;
}
