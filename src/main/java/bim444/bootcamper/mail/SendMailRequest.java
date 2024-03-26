package bim444.bootcamper.mail;

public record SendMailRequest(String to, String text, String subject) {
}
