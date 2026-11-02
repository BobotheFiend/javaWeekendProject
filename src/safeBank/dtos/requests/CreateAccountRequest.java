package safeBank.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String pin;

}
