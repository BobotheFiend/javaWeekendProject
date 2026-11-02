package safeBank.dtos.requests;

import lombok.Data;

@Data
public class WithdrawalRequest {

    String amount;
    String accountNumber;
    String pin;

}
