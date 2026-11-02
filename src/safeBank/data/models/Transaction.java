package safeBank.data.models;

import lombok.Data;
import safeBank.data.models.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class Transaction {

    private String id;
    private String accountNumber;
    private BigDecimal amount;
    private TransactionType type;
    private LocalDateTime dateTime;

}
