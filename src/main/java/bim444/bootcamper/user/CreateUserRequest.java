package bim444.bootcamper.user;

public record CreateUserRequest(
        String name,
        //todo validation islemlerini unutma
        String email,
        String password
) {
}
