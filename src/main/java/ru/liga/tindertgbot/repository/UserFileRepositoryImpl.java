package ru.liga.tindertgbot.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.liga.tindertgbot.entity.User;
import ru.liga.tindertgbot.exception.StorageException;

import java.io.File;
import java.io.IOException;

@Service
//todo почему у тебя данные хранятся в файлах, а не в базе данных
// надо переделать под БД
public class UserFileRepositoryImpl implements UserFileRepository {
    private final ObjectMapper mapper;

    @Value("${store.directory}")
    private String storageDirectory;

    public UserFileRepositoryImpl() {
        this.mapper = new ObjectMapper();
    }

    public void setStorageDirectory(String directory) {
        this.storageDirectory = directory;
    }

    @Override
    public void store(User user) {
        try {
            mapper.writeValue(new File(fileName(user.getChatId())), user);
        } catch (IOException exception) {
            throw new StorageException(
                    "Не удалось сохранить файл в хранилище: " + fileName(user.getChatId()),
                    exception
            );
        }
    }

    @Override
    public User load(long userId) {
        try {
            return mapper.readValue(new File(fileName(userId)), User.class);
        } catch (IOException exception) {
            throw new StorageException(
                    "Не удалось прочитать файл из хранилища: " + fileName(userId),
                    exception
            );
        }
    }

    private String fileName (long UserId) {
        return storageDirectory + "/" + UserId + ".json";
    }
}