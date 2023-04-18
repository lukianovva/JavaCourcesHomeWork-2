package ru.liga.tindertgbot.service;

import ru.liga.tindertgbot.dto.UserDto;

public interface UserTemporaryStorageService {
    void store(UserDto user);

    UserDto load(int userId);
}
