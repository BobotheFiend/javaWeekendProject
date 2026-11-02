package safeBank.dtos.requests;

import lombok.Data;

@Data
public class DepositRequest {

    String amount;
    String accountNumber;
}
