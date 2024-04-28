package bim444.bootcamper.user;

import bim444.bootcamper.validator.PasswordConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
       @NotBlank String name,
       @NotBlank  @Email(regexp = ".+@.+\\..+",message = "invalid e-mail") String email,
       @NotBlank Role role,
       @PasswordConstraint  @NotBlank String password
) {
}
