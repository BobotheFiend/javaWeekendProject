package safeBank.data.models;

import lombok.Data;
import safeBank.data.models.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Transaction {

    private String transactionId;
    private String accountId;
    private BigDecimal amount;
    private String description;
    private TransactionType type;
    private LocalDate date;
    private LocalTime time;

}
