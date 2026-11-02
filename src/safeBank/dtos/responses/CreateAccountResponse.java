package safeBank.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountResponse {
    String fullName;
    BigDecimal openingBalance;
    String number;
}
