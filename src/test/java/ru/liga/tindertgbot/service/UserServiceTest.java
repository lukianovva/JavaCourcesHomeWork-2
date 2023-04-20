package ru.liga.tindertgbot.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.liga.tindertgbot.dict.Sex;
import ru.liga.tindertgbot.entity.User;
import ru.liga.tindertgbot.repository.UserFileRepository;
import ru.liga.tindertgbot.repository.UserFileRepositoryImpl;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

class UserServiceTest {
    private static UserService userService;

    private final static String STORAGE_DIRECTORY = "test_storage";

    @BeforeAll
    static void initStorage() {
        File file = new File(STORAGE_DIRECTORY);
        if (!file.exists() || !file.isDirectory()) {
            if (!file.mkdir()) {
                throw new RuntimeException("Невозможно создать директорию " + STORAGE_DIRECTORY);
            }
        }
        UserFileRepository storage = new UserFileRepositoryImpl();
        storage.setStorageDirectory(STORAGE_DIRECTORY);

        userService = new UserServiceImpl(storage);

        storage.store(User.builder().chatId(100).build());
    }

    @Test
    void setSex() {
        User user = userService.setSex(100, Sex.MALE);

        assertThat(user.getSex()).isEqualTo(Sex.MALE);
    }

    @Test
    void setName() {
        User user = userService.setName(100, "Name");

        assertThat(user.getName()).isEqualTo("Name");
    }

    @Test
    void setDescription() {
        User user = userService.setDescription(100, "Description");

        assertThat(user.getDescription()).isEqualTo("Description");
    }

    @Test
    void setPreferences() {
        User user = userService.setPreference(100, Sex.FEMALE);

        assertThat(user.getPreference()).isEqualTo(Sex.FEMALE);
    }
}