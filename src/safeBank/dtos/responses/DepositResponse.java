package safeBank.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DepositResponse {
    private String amountDeposited;
    LocalDateTime dateTime;
}
