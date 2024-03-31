package bim444.bootcamper.userbootcamp;

import jakarta.validation.constraints.NotBlank;

public record CreateUserBootcampRequest(
       @NotBlank String userId,
       @NotBlank String baseBootcampId
) {
}
