package ru.liga.tindertgbot.client;

import org.springframework.stereotype.Service;
import ru.liga.tindertgbot.entity.User;

@Service
public interface UserClient {
    User get(long chatId, long userId);

    User create(long chatId, User user);

    User update(long chatId, User user);

    User like(long chatId, int userId);

    User searchNext(long chatId,int userId);

    User searchWithLikeNext(long chatId, int userId);

    User searchWithLikePrev(long chatId, int userId);
}
