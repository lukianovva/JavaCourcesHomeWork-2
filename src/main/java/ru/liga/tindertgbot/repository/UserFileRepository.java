package ru.liga.tindertgbot.repository;

import ru.liga.tindertgbot.entity.User;

public interface UserFileRepository {
    void store(User user);

    User load(int userId);

    void setStorageDirectory(String directory);
}
