package ru.liga.tindertgbot.exception;

public class StorageException extends RuntimeException {
    public StorageException(String message, Exception exception) {
        super(message, exception);
    }
}
