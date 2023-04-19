package ru.liga.tindertgbot.exceptions;

public class StorageException extends RuntimeException {
    public StorageException(String message, Exception exception) {
        super(message, exception);
    }
}
