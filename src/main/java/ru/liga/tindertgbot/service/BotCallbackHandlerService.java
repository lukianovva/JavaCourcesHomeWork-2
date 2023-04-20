package ru.liga.tindertgbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.tindertgbot.bot.BotCallbackAction;
import ru.liga.tindertgbot.dict.Sex;
import ru.liga.tindertgbot.entity.User;

@Service
public class BotCallbackHandlerService extends AbstractBotHandler {

    private UserService userService;

    public BotCallbackHandlerService(UserService userService) {
        this.userService = userService;
    }

    public SendMessage handle(long chatId, BotCallbackAction action, String value) {
        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder();
        sendMessageBuilder.chatId(chatId).text("");

        switch (action) {
            case SET_SEX -> {
                setSex(chatId, Sex.valueOf(value.toUpperCase()), sendMessageBuilder);
            }
            case SET_PREFERENCE -> {
                setPreference(chatId, Sex.valueOf(value.toUpperCase()), sendMessageBuilder);
            }
        }

        return sendMessageBuilder.build();
    }

    private void setSex(long chatId, Sex sex, SendMessage.SendMessageBuilder response) {
        User user = userService.setSex(chatId, sex);

        buildFillUserDataResponse(user.getNextEmptyField(), response);
    }

    private void setPreference(long chatId, Sex sex, SendMessage.SendMessageBuilder response) {
        User user = userService.setPreference(chatId, sex);

        buildFillUserDataResponse(user.getNextEmptyField(), response);
    }
}
