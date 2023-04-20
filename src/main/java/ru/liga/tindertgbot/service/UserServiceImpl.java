package ru.liga.tindertgbot.service;


import org.springframework.stereotype.Service;
import ru.liga.tindertgbot.dict.Sex;
import ru.liga.tindertgbot.entity.User;
import ru.liga.tindertgbot.exception.StorageException;
import ru.liga.tindertgbot.repository.UserFileRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserFileRepository storage;

    public UserServiceImpl(UserFileRepository storage) {
        this.storage = storage;
    }

    @Override
    public User setSex(int userId, Sex sex) {
        User user = getUser(userId);
        user.setSex(sex);

        storage.store(user);

        return user;
    }

    @Override
    public User setName(int userId, String name) {
        User user = getUser(userId);
        user.setName(name);

        storage.store(user);

        return user;
    }

    @Override
    public User setDescription(int userId, String description) {
        User user = getUser(userId);
        user.setDescription(description);

        storage.store(user);

        return user;
    }

    @Override
    public User setPreference(int userId, Sex sex) {
        User user = getUser(userId);
        user.setPreference(sex);

        storage.store(user);

        return user;
    }

    private User getUser(int userId) {
        User user;
        try {
            user = storage.load(userId);
        } catch (StorageException storageException) {
            user = new User();
        }

        return user;
    }
}
