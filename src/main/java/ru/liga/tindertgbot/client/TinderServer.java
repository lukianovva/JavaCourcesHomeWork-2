package ru.liga.tindertgbot.client;

import org.springframework.stereotype.Service;
import ru.liga.tindertgbot.dto.UserDto;

@Service
public interface TinderServer {

    UserDto loadUser(int userId);

    boolean saveUser(UserDto user);

    boolean like(int userId);

    UserDto searchNext(int userId);

    UserDto searchLikeNext(int userId);

    UserDto searchLikePrev(int userId);
}
