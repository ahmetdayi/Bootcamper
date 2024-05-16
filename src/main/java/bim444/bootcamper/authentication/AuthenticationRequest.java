package bim444.bootcamper.authentication;


import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank String email,

        @NotBlank String password
) {
}
