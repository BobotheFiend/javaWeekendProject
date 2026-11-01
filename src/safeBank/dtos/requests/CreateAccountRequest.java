package safeBank.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    
    String firstName;
    String lastName;
    BigDecimal openingBalance;
}
