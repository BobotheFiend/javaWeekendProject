package safeBank.dtos.responses;

import lombok.Data;
import safeBank.data.models.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DepositResponse {
    private String amountDeposited;
    LocalDateTime dateTime;
    String transactionId;
    String accountNumber;
    String bankName;
    TransactionType transactionType;
}
