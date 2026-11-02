package safeBank.dtos.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private  String accountNumber;
    private String pin;
}
