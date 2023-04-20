package ru.liga.tindertgbot.bot;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.tindertgbot.service.BotCommandHandlerService;

import java.util.Optional;

@Component
public class TinderBot extends TelegramLongPollingBot {

    private final BotCommandHandlerService botCommandHandlerService;
    @Value("${bot.name}")
    private String name;
    @Value("${bot.token}")
    private String token;

    public TinderBot(BotCommandHandlerService botCommandHandlerService) {
        this.botCommandHandlerService = botCommandHandlerService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) {
            return;
        }

        try {
            handleMessage(update.getMessage());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleMessage(Message message) throws TelegramApiException {
        if (!message.hasText() && !message.hasEntities()) {
            return;
        }

        Optional<MessageEntity> commandEntity = message.getEntities()
                .stream()
                .filter(entity -> "bot_command".equals(entity.getType()))
                .findFirst();

        if (commandEntity.isEmpty()) {
            return;
        }

        BotCommand command = BotCommand.valueOf(
                message.getText()
                        .substring(commandEntity.get().getOffset() + 1, commandEntity.get().getLength())
                        .toUpperCase()
        );

        SendMessage response = botCommandHandlerService.handle(message.getChatId(), command);

        execute(response);
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
