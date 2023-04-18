package ru.liga.tindertgbot.service;

import ru.liga.tindertgbot.dicts.UserSex;

public interface UserService {
    void setSex(int userId, UserSex sex);

    void setName(int userId, String name);

    void setDescription(int userId, String description);

    void setPreferences(int userId, UserSex sex);

    String generateHeaderFromDescription(String description);
}
