package ru.liga.tindertgbot.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.liga.tindertgbot.entity.User;
import ru.liga.tindertgbot.exception.StorageException;

import java.io.File;
import java.io.IOException;

@Service
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
                    "Не удалось сохранить файл в хранилище для " + user.getChatId(),
                    exception
            );
        }
    }

    @Override
    public User load(int userId) {
        try {
            return mapper.readValue(new File(fileName(userId)), User.class);
        } catch (IOException exception) {
            throw new StorageException(
                    "Не удалось прочитать файл из хранилища для " + userId,
                    exception
            );
        }
    }

    private String fileName (int UserId) {
        return storageDirectory + "/" + UserId + ".json";
    }
}