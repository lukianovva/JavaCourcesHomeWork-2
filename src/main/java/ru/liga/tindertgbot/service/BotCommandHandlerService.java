package ru.liga.tindertgbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.tindertgbot.bot.BotCommand;
import ru.liga.tindertgbot.entity.User;

@Service
public class BotCommandHandlerService extends AbstractBotHandler {
    private UserService userService;

    public BotCommandHandlerService(UserService userService) {
        this.userService = userService;
    }

    public SendMessage handle(long chatId, BotCommand command) {
        switch (command) {
            case START -> {
                return start(chatId);
            }
        }

        return new SendMessage();
    }

    private SendMessage start(long chatId) {
        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder();
        sendMessageBuilder.chatId(chatId);

        User user = userService.get(chatId);

        buildFillUserDataResponse(user.getNextEmptyField(), sendMessageBuilder);

        return sendMessageBuilder.build();
    }
}
