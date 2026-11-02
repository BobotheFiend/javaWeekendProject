package safeBank.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WithdrawalResponse {
    String amountWithdrew;
    LocalDateTime dateTime;

}
