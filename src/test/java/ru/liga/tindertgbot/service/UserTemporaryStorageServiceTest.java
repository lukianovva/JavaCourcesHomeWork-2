package ru.liga.tindertgbot.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.tindertgbot.dicts.UserSex;
import ru.liga.tindertgbot.dto.UserDto;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;


class UserTemporaryStorageServiceTest {
    public static UserTemporaryStorageService storage;

    private final static String STORAGE_DIRECTORY = "test_storage";

    @BeforeAll
    static void initStorage() {
        File file = new File(STORAGE_DIRECTORY);
        if (!file.exists() || !file.isDirectory()) {
            if (!file.mkdir()) {
                throw new RuntimeException("Невозможно создать директорию " + STORAGE_DIRECTORY);
            }
        }
        storage = new UserTemporaryStorageServiceImpl(STORAGE_DIRECTORY);
    }

    @Test
    void store() {
        UserDto user = new UserDto(
                100,
                "Name",
                UserSex.MALE,
                "Header",
                "Description",
                UserSex.FEMALE
        );

        storage.store(user);
    }

    @Test
    void load() {
        UserDto user = storage.load(100);

        assertThat(user).isEqualTo(new UserDto(
                100,
                "Name",
                UserSex.MALE,
                "Header",
                "Description",
                UserSex.FEMALE
        ));
    }
}