package ru.liga.tindertgbot.bot;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.tindertgbot.service.BotCallbackHandlerService;
import ru.liga.tindertgbot.service.BotCommandHandlerService;
import ru.liga.tindertgbot.service.BotTextHandlerService;

import java.util.Optional;

@Component
public class Bot extends TelegramLongPollingBot {

    private final BotCommandHandlerService botCommandHandlerService;
    private final BotTextHandlerService botTextHandlerService;
    private final BotCallbackHandlerService botCallbackHandlerService;

    @Value("${bot.name}")
    private String name;
    @Value("${bot.token}")
    private String token;

    public Bot(BotCommandHandlerService botCommandHandlerService, BotTextHandlerService botTextHandlerService, BotCallbackHandlerService botCallbackHandlerService) {
        this.botCommandHandlerService = botCommandHandlerService;
        this.botTextHandlerService = botTextHandlerService;
        this.botCallbackHandlerService = botCallbackHandlerService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
        } else if (update.hasMessage() && update.getMessage().hasEntities()) {
            handleCommand(update.getMessage());
        } else if (update.hasMessage()) {
            handleText(update.getMessage());
        }
    }

    @SneakyThrows
    private void handleCommand(Message message) {
        if (!message.hasEntities()) {
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

    @SneakyThrows
    private void handleText(Message message) {
        if (!message.hasText()) {
            return;
        }

        SendMessage response = botTextHandlerService.handle(message.getChatId(), message.getText());
        execute(response);
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String[] param = callbackQuery.getData().split(":");
        BotCallbackAction action = BotCallbackAction.valueOf(param[0].toUpperCase());

        SendMessage response = botCallbackHandlerService.handle(message.getChatId(), action, param[1]);

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
