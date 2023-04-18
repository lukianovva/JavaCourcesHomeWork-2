package ru.liga.tindertgbot;

import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.liga.tindertgbot.bot.TinderBot;

@SpringBootApplication
public class TinderTgBot {
    @SneakyThrows
    public static void main(String[] args) {
        TinderBot bot = new TinderBot();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
}