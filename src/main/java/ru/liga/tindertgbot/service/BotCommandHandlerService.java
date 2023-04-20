package ru.liga.tindertgbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.liga.tindertgbot.bot.BotCommand;

@Service
public class BotCommandHandlerService {
    public SendMessage handle(long chatId, BotCommand command) {
        switch (command) {
            case START -> {
                return start(chatId);
            }
        }

        return new SendMessage();
    }

    private SendMessage start(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        message.setText("Привет!");

        return message;
    }
}
