package ru.liga.tindertgbot.bot;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TinderBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return "@PrerevolutionaryTinderHW3Bot";
    }

    @Override
    public String getBotToken() {
        return "6133840785:AAG4tdjsD1rST89Bw3EMKoh1SkNxMkfwlhw";
    }
}
