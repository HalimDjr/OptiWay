package fr.uga.miage.l3.exceptions.rest;

public class NotFoundCommandRestException extends RuntimeException {
    public NotFoundCommandRestException(String message) {
        super(message);
    }
}
