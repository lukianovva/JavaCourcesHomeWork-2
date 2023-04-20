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
    public User setSex(long userId, Sex sex) {
        User user = get(userId);
        user.setSex(sex);

        storage.store(user);

        return user;
    }

    @Override
    public User setName(long userId, String name) {
        User user = get(userId);
        user.setName(name);

        storage.store(user);

        return user;
    }

    @Override
    public User setDescription(long userId, String description) {
        User user = get(userId);
        user.setDescription(description);

        storage.store(user);

        return user;
    }

    @Override
    public User setPreference(long userId, Sex sex) {
        User user = get(userId);
        user.setPreference(sex);

        storage.store(user);

        return user;
    }

    public User get(long userId) {
        User user;
        try {
            user = storage.load(userId);
        } catch (StorageException storageException) {
            user = new User();
            user.setChatId(userId);
        }

        return user;
    }
}
