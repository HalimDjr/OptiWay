package fr.uga.miage.l3.exceptions.technical;

public class NotFoundCommandException extends RuntimeException {
    public NotFoundCommandException(String message) {
        super(message);
    }
}
