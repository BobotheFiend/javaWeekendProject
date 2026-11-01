package safeBank.data.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {

    private String fullName;
    private BigDecimal balance;
    
}
