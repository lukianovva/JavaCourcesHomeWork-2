package ru.liga.tindertgbot.service;

import ru.liga.tindertgbot.dict.Sex;
import ru.liga.tindertgbot.entity.User;

public interface UserService {
    User setSex(long userId, Sex sex);

    User setName(long userId, String name);

    User setDescription(long userId, String description);

    User setPreference(long userId, Sex sex);

    User get(long userId);
}
