package ru.liga.tindertgbot.service;

import ru.liga.tindertgbot.dicts.UserSex;

public interface UserService {
    void setSex(String userId, UserSex sex);

    void setName(String userId, String name);

    void setDescription(String userId, String description);

    void setPreferences(String userId, UserSex sex);
}
