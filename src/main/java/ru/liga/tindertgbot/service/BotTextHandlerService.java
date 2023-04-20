package ru.liga.tindertgbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class BotTextHandlerService extends AbstractBotHandler {

    private UserService userService;

    public BotTextHandlerService(UserService userService) {
        this.userService = userService;
    }

    public SendMessage handle(long chatId, String text) {
        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder();
        sendMessageBuilder.chatId(chatId).text("Обработчик отсутсвует");

        return sendMessageBuilder.build();
    }
}
