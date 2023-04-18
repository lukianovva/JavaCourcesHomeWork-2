package ru.liga.tindertgbot.service;

import ru.liga.tindertgbot.dto.UserDto;

public interface UserStorageService {
    void store(UserDto user);

    UserDto load(String userId);
}
