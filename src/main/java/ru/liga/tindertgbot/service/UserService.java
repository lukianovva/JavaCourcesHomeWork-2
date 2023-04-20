package ru.liga.tindertgbot.service;

import ru.liga.tindertgbot.dict.Sex;
import ru.liga.tindertgbot.entity.User;

public interface UserService {
    User setSex(int userId, Sex sex);

    User setName(int userId, String name);

    User setDescription(int userId, String description);

    User setPreference(int userId, Sex sex);
}
