package ru.liga.tindertgbot.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.tindertgbot.dict.Sex;
import ru.liga.tindertgbot.entity.User;
import ru.liga.tindertgbot.exception.StorageException;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;


class UserFileRepositoryTest {
    public static UserFileRepository storage;

    private final static String STORAGE_DIRECTORY = "test_storage";

    @BeforeAll
    static void initStorage() {
        File file = new File(STORAGE_DIRECTORY);
        if (!file.exists() || !file.isDirectory()) {
            if (!file.mkdir()) {
                throw new RuntimeException("Невозможно создать директорию " + STORAGE_DIRECTORY);
            }
        }
        storage = new UserFileRepositoryImpl();
        storage.setStorageDirectory(STORAGE_DIRECTORY);
    }

    @Test
    void store() {
        User user = new User(
                100,
                "Name",
                Sex.MALE,
                "Header",
                "Description",
                Sex.FEMALE
        );

        storage.store(user);
    }

    @Test
    void load() {
        User user = storage.load(100);

        assertThat(user).isEqualTo(new User(
                100,
                "Name",
                Sex.MALE,
                "Header",
                "Description",
                Sex.FEMALE
        ));
    }

    @Test
    void loadNotExistsFile() {
        assertThatException()
                .isThrownBy(() -> {
                    storage.load(200);
                })
                .isExactlyInstanceOf(StorageException.class)
                .withMessageContaining("Не удалось прочитать файл из хранилища");
    }
}