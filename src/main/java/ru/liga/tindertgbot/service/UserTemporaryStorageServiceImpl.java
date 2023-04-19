package ru.liga.tindertgbot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.liga.tindertgbot.dto.UserDto;
import ru.liga.tindertgbot.exceptions.StorageException;

import java.io.File;
import java.io.IOException;

public class UserTemporaryStorageServiceImpl  implements UserTemporaryStorageService {
    private final ObjectMapper mapper;
    private final String storageDirectory;

    public UserTemporaryStorageServiceImpl(String storageDirectory) {
        this.storageDirectory = storageDirectory;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void store(UserDto user) {
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
    public UserDto load(int userId) {
        try {
            return mapper.readValue(new File(fileName(userId)), UserDto.class);
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