package ru.liga.tindertgbot.service;


import org.springframework.stereotype.Service;
import ru.liga.tindertgbot.dicts.UserSex;
import ru.liga.tindertgbot.dto.UserDto;
import ru.liga.tindertgbot.exceptions.StorageException;

@Service
public class UserServiceImpl implements UserService {

    private final UserTemporaryStorageService storage;

    public UserServiceImpl(UserTemporaryStorageService storage) {
        this.storage = storage;
    }

    @Override
    public void setSex(int userId, UserSex sex) {
        UserDto user = getUser(userId);
        user.setSex(sex);

        storage.store(user);
    }

    @Override
    public void setName(int userId, String name) {
        UserDto user = getUser(userId);
        user.setName(name);

        storage.store(user);
    }

    @Override
    public void setDescription(int userId, String description) {
        UserDto user = getUser(userId);
        user.setDescription(description);

        storage.store(user);
    }

    @Override
    public void setPreferences(int userId, UserSex sex) {
        UserDto user = getUser(userId);
        user.setPreference(sex);

        storage.store(user);
    }

    @Override
    public String generateHeaderFromDescription(String description) {
        return null;
    }

    private UserDto getUser(int userId) {
        UserDto user;
        try {
            user = storage.load(userId);
        } catch (StorageException storageException) {
            user = new UserDto();
        }

        return user;
    }
}
